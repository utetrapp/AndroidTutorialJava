package de.h_da.fbi.activitycommunication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EditStudentActivity extends AppCompatActivity {

    private Student student;
    private EditText editCity, editName;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        
        editCity = findViewById(R.id.editTextCity);
        editName = findViewById(R.id.editTextName);

        Button btn = findViewById(R.id.btnSave);
        btn.setOnClickListener(view -> saveData());

        //@see https://developer.android.com/training/basics/firstapp/starting-activity#java
        student = (Student) getIntent().getSerializableExtra("student");
        if (student != null){
            editCity.setText(student.getCity());
            editName.setText(student.getName());
        }

    }

    private void saveData(){
        student.setCity(editCity.getText().toString());
        student.setName(editName.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra("student", student);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}