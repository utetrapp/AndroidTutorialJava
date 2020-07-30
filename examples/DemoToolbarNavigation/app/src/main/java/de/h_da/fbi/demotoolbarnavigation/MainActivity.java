package de.h_da.fbi.demotoolbarnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //NavigationDrawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        //define listeners
        MenuItem menuItem = menu.findItem(R.id.action_nav_a);
        if (menuItem != null) {
            menuItem.setOnMenuItemClickListener(item -> {
                Intent intent = new Intent(this, ActivityA.class);
                startActivity(intent);
                return true;
            });
        }
        menuItem = menu.findItem(R.id.action_nav_b);
        if (menuItem != null) {
            menuItem.setOnMenuItemClickListener(item -> {
                Intent intent = new Intent(this, ActivityB.class);
                startActivity(intent);
                return true;
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (id == R.id.nav_activitya) {
            drawer.closeDrawer(GravityCompat.START, false);
            Intent intent = new Intent(this, ActivityA.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.nav_activityb) {
            drawer.closeDrawer(GravityCompat.START, false);
            Intent intent = new Intent(this, ActivityB.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}