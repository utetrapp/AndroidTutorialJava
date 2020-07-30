package de.h_da.fbi.demotoolbarnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityB extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle(getString(R.string.menu_activityb));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}