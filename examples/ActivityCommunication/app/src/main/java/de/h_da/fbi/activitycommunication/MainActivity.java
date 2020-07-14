package de.h_da.fbi.activitycommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btnNavigateToStudents);
        btn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, StudentsActivity.class)));
    }
}