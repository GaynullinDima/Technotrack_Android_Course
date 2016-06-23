package ru.mail.track.hw2.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ru.mail.track.hw2.Api;
import ru.mail.track.hw2.R;
import ru.mail.track.hw2.StreamUtils;
import ru.mail.track.hw2.TechItem;
import ru.mail.track.hw2.TechParser;

public class SplashActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<TechItem>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public Loader<List<TechItem>> onCreateLoader(int id, Bundle args) {
        return new LoadDataAsyncTask(SplashActivity.this);
    }

    @Override
    public void onLoaderReset(Loader<List<TechItem>> loader) {

    }

    @Override
    public void onLoadFinished(Loader<List<TechItem>> loader, List<TechItem> data) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("technologies",(ArrayList<TechItem>) data);

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private static class LoadDataAsyncTask extends AsyncTaskLoader<List<TechItem>> {
        public LoadDataAsyncTask(Context context) {
            super(context);
        }

        @Override
        public List<TechItem> loadInBackground() {
            List<TechItem> items = new ArrayList<>();
            URL url = null;
            try {
                url = new URL(Api.DATA_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream in = new BufferedInputStream(connection.getInputStream());
                String data = StreamUtils.readStream(in);
                in.close();

                items = TechParser.parse(data);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return items;
        }
    }
}
