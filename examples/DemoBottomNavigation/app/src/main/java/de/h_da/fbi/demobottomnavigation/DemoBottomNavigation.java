package de.h_da.fbi.demobottomnavigation;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DemoBottomNavigation implements BottomNavigationView.OnNavigationItemSelectedListener {
    final Context parent;

    public DemoBottomNavigation(Context parent) {
        this.parent = parent;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //verschiedene Flags sind möglich (FLAG_ACTIVITY_NO_HISTORY, FLAG_ACTIVITY_CLEAR_TOP, FLAG_ACTIVITY_REORDER_TO_FRONT),
        // auch das Attribut android:launchMode="singleTask" im Manifest ist möglich
        // wichtig ist, dass der Stack nicht mit jedem Wechsel in der BottomNavigation anwächst
        if (id == R.id.bottom_nav_home) {
            Intent intent = new Intent(parent, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
            parent.startActivity(intent);
            return true;
        }
        if (id == R.id.bottom_nav_a) {
            Intent intent = new Intent(parent, ActivityA.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION); //avoid activity stack, hence no back possible!
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION); //avoid activity stack, hence no back possible!
            parent.startActivity(intent);
            return true;
        }
        if (id == R.id.bottom_nav_b) {
            Intent intent = new Intent(parent, ActivityB.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            parent.startActivity(intent);
            return true;
        }
        return false;
    }
}
