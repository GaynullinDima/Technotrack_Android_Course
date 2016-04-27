package ru.mail.track.k33p.androidcourse1_homework2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {
    private LoadDataTask mLoadDataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mLoadDataTask == null) {
            mLoadDataTask = new LoadDataTask(this);
            mLoadDataTask.execute();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelDataLoading();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelDataLoading();
    }

    private void cancelDataLoading() {
        if (mLoadDataTask != null) {
            mLoadDataTask.cancel(true);
            mLoadDataTask = null;
        }
    }

    @Override
    public void onDataLoaded() {
        Intent intent = new Intent(SplashScreenActivity.this, MainScreenActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDataLoadingFailed(int errorResId) {
        Toast.makeText(getApplicationContext(), errorResId, Toast.LENGTH_LONG).show();
    }


    private static class LoadDataTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<DataLoadingListener> mListenerWeakRef;
        private int mErrorResId = R.string.error_load;

        LoadDataTask(DataLoadingListener listener) {
            mListenerWeakRef = new WeakReference<>(listener);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            HttpURLConnection conn = null;
            String DATA = .getString(R.string.DATA_URL);
            try {
                URL url = new URL(R.string.BASE_URL);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                InputStream in = new BufferedInputStream(conn.getInputStream());
                String data = StreamUtils.readStream(in);
                in.close();

                List<Item> items = ItemParser.parse(data);
                DbHelper dbHelper = MainApplication.getDbHelper();
                dbHelper.deleteTechnologies();
                dbHelper.insertTechnologies(items);

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                mErrorResId = R.string.error_read;
            } catch (JSONException e) {
                e.printStackTrace();
                mErrorResId = R.string.error_parse;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!isCancelled()) {
                DataLoadingListener listener = mListenerWeakRef.get();
                if (listener != null) {
                    if (result) {
                        listener.onDataLoaded();
                    } else {
                        listener.onDataLoadingFailed(mErrorResId);
                    }
                }
            }
        }
    }
}
