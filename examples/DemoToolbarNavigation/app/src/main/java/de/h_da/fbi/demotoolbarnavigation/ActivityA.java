package de.h_da.fbi.demotoolbarnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityA extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setTitle(getString(R.string.menu_activitya));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}