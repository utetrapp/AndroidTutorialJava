package de.h_da.fbi.activitycommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StudentsActivity extends AppCompatActivity {
    private static int ACTIVITY_RESULT = 42;
    private Student student1, student2;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btn1 = findViewById(R.id.btnNavigateToEditStudent1);
        student1 = new Student(1,"Max", "Darmstadt");
        btn1.setOnClickListener(view -> navigateToEditStudent(student1));
        Button btn2 = findViewById(R.id.btnNavigateToEditStudent2);
        student2 = new Student(2,"Maria", "Frankfurt");
        btn2.setOnClickListener(view -> navigateToEditStudent(student2));

        showStudent1();
        showStudent2();
    }

    private void navigateToEditStudent(Student student) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        intent.putExtra("student", student);
        this.startActivityForResult(intent, ACTIVITY_RESULT);
    }

    private void showStudent1(){
        TextView txt = findViewById(R.id.textViewStudent1);
        txt.setText(student1.toString());
    }
    private void showStudent2(){
        TextView txt = findViewById(R.id.textViewStudent2);
        txt.setText(student2.toString());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Student student = (Student) data.getSerializableExtra("student");
            if (student != null) {
               if(student.getId() == 1) {
                   student1 = student;
                   showStudent1();
               }
               else if (student.getId() == 2) {
                   student2 = student;
                   showStudent2();
               }
            }

        }
    }
}