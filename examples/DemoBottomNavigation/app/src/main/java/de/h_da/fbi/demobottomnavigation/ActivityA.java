package de.h_da.fbi.demobottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityA extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        bottomNav = findViewById(R.id.nav_bottom);
        bottomNav.setOnItemSelectedListener(new DemoBottomNavigation(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.getMenu().findItem(R.id.bottom_nav_a).setChecked(true);
    }
}