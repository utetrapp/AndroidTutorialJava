package de.h_da.fbi.demosettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private EditText editWeight, editSize;
    private TextView txtBmi;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editTextWeight);
        editSize = findViewById(R.id.editTextSize);
        txtBmi = findViewById(R.id.textViewBmi);

        Button btnCalculate = findViewById(R.id.buttonCalculate);
        btnCalculate.setOnClickListener(view -> bmiHandler());

        //so gleiche Setting in allen Activities
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //so nur in dieser Activity/diesem Kontext
        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        float weight = sharedPref.getFloat(getString(R.string.shared_pref_key_weight), 60);
        int height = sharedPref.getInt(getString(R.string.shared_pref_key_height), 170);
        editWeight.setText(String.valueOf(weight));
        editSize.setText(String.valueOf(height));
    }

    private void bmiHandler() {
        if (isValid()) {
            int size = Integer.parseInt(editSize.getText().toString());
            float weight = Float.parseFloat(editWeight.getText().toString());
            setBmi(getBmi(size, weight));
            saveToPreferences(size, weight);
        }
    }

    private void saveToPreferences(int size, float weight) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(getString(R.string.shared_pref_key_weight), weight);
        editor.putInt(getString(R.string.shared_pref_key_height), size);
        editor.apply();
    }

    private boolean isValid() {
        if (editWeight.getText().toString().isEmpty()) {
            editWeight.setError(getString(R.string.warning_weight));
            return false;
        }
        if (editSize.getText().toString().isEmpty()) {
            editSize.setError(getString(R.string.warning_size));
            return false;
        }
        return true;
    }

    private float getBmi(int size, float weight) {
        float sizeInm = size / 100.0f;
        return weight / (sizeInm * sizeInm);
    }

    private void setBmi(float bmi) {
        txtBmi.setText(String.format("BMI: %.1f", bmi));
    }


    public void gotoSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}