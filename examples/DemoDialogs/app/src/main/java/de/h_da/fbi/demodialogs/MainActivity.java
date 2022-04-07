package de.h_da.fbi.demodialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView textViewNumber, textViewDate;
    private View layoutMain;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNumber = findViewById(R.id.textViewSelectedNumber);
        textViewDate = findViewById(R.id.textViewSelectedDate);
        dateFormat = new SimpleDateFormat(getString(R.string.date_format_dmy));
        layoutMain = findViewById(R.id.layoutMain);

        Button btnToast = findViewById(R.id.btnToast);
        btnToast.setOnClickListener(view -> showToast("hello world"));
        Button btnSnackbar = findViewById(R.id.btnSnackbar);
        btnSnackbar.setOnClickListener(view -> showSnackbar());
        Button btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(view -> showAlertDialog());
        Button btnPickNumber = findViewById(R.id.btnNumber);
        btnPickNumber.setOnClickListener(view -> pickNumber());
        Button btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(view -> pickDate());
    }

    private void showToast(String message) {
        //@see https://developer.android.com/guide/topics/ui/notifiers/toasts
        (Toast.makeText(this, message, Toast.LENGTH_LONG)).show();
    }

    private void showSnackbar(){
        //snackbar better with coordinator layout
        Snackbar.make(layoutMain, "hi",
                Snackbar.LENGTH_INDEFINITE).setAction("ok", view -> showToast("pressed ok in snackbar")).show();
    }

    private void showAlertDialog() {
        //@see https://developer.android.com/guide/topics/ui/dialogs#AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("hello world")
                .setTitle("Title");
        builder.setPositiveButton("ok", (dialog, id) -> {
            // User clicked OK button
            showToast("you pressed ok");
        });
        builder.setNegativeButton("cancel", (dialog, id) -> {
            // User cancelled the dialog
            showToast("you pressed cancel");
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void pickNumber() {
        //@see https://developer.android.com/guide/topics/ui/dialogs#AlertDialog and
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.number_picker_dialog, null);
        builder.setView(view);
        NumberPicker numberPicker = view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(1);
        final int[] selectedNumber = new int[1];
        numberPicker.setOnValueChangedListener((numberPicker1, oldValue, newValue) -> selectedNumber[0] = newValue);
        builder.setMessage("Set your age")
                .setTitle("Age");
        builder.setPositiveButton("set", (dialog, id) -> textViewNumber.setText(String.valueOf(selectedNumber[0])));
        //builder.setNegativeButton("cancel", (dialog, id) -> {   });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void pickDate() {
        //@see https://developer.android.com/guide/topics/ui/controls/pickers
        //we use MaterialDesign
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker(); //for date range use MaterialDatePicker.Builder.dateRangePicker()
        MaterialDatePicker<Long> picker = builder.build();
        //picker.addOnCancelListener(dialog -> {   });
        picker.addOnPositiveButtonClickListener(dateLong -> {
            //with api 26 you could use  LocalDate localDate
            Date date = new Date();
            date.setTime(dateLong);
            textViewDate.setText(dateFormat.format(date));
        });
        picker.show(getSupportFragmentManager(), picker.toString());
    }


}