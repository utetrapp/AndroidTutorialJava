package de.h_da.fbi.demobottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BottomNavigation
        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        bottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //Bottom navigation
        if (id == R.id.bottom_nav_a) {
            Intent intent = new Intent(this, ActivityA.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.bottom_nav_b) {
            Intent intent = new Intent(this, ActivityB.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}