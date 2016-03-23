package homework1.k33p.track.mail.ru.homework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    private volatile boolean ButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!ButtonBack) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }

                    Intent intent = new Intent(StartActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ButtonBack = true;
    }
}
