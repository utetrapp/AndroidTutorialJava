package de.h_da.fbi.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editWeight, editSize;
    private TextView txtBmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editTextWeight);
        editSize = findViewById(R.id.editTextSize);
        txtBmi = findViewById(R.id.textViewBmi);

        Button btnCalculate = findViewById(R.id.buttonCalculate);
        btnCalculate.setOnClickListener(view -> bmiHandler());
    }

    private void bmiHandler() {
        if (isValid()){
            int size = Integer.parseInt(editSize.getText().toString());
            float weight = Float.parseFloat(editWeight.getText().toString());
            setBmi(getBmi(size, weight));
        }
    }

    private boolean isValid(){
        if (editWeight.getText().toString().isEmpty()){
            editWeight.setError(getString(R.string.warning_weight));
            return false;
        }
        if (editSize.getText().toString().isEmpty()){
            editSize.setError(getString(R.string.warning_size));
            return false;
        }
        return true;
    }

    private float getBmi(int size, float weight){
        float sizeInm = size/100.0f;
        return weight/(sizeInm * sizeInm);
    }

    private void setBmi(float bmi){
        txtBmi.setText(String.format("BMI: %.1f", bmi));
    }

}