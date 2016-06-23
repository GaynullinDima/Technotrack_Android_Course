package ru.mail.track.hw2.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.mail.track.hw2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            TechRecycleViewFragment techRecycleViewFragment = new TechRecycleViewFragment();
            techRecycleViewFragment.setArguments(this.getIntent().getExtras());
            ft.replace(R.id.fragment_container, techRecycleViewFragment, techRecycleViewFragment.getTag());
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
