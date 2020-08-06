package de.h_da.fbi.demoroom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import de.h_da.fbi.demoroom.model.AppDatabase;
import de.h_da.fbi.demoroom.model.City;

public class CityDetailsActivity extends AppCompatActivity {
    private City city = null;
    AppDatabase db;
    private EditText editTitle, editDescription, editInhabitants;
    private ImageView imageViewCity;
    private Spinner spinner;
    private static final int REQUEST_PICK_IMAGE = 42;
    private String imagePath = "";
    private Bitmap imageBitmap = null;

    //handle click on  back arrow
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        //show back arrow in title bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitle = findViewById(R.id.editTitle);
        editInhabitants = findViewById(R.id.editInhabitants);
        editDescription = findViewById(R.id.editDescription);


        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setVisibility(View.GONE);
        Button btnUpdate = findViewById(R.id.btnSave);
        btnUpdate.setOnClickListener(view -> saveItem());
        spinner = findViewById(R.id.spinnerContinents);
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, City.Continent.values()));
        spinner.setSelected(false);
        imageViewCity = findViewById(R.id.imageView);
        imageViewCity.setOnClickListener(view -> selectImage());

        city = (City) getIntent().getSerializableExtra("city");
        if (city != null) {
            editInhabitants.setText(String.format("%,d", city.getInhabitants()));
            editDescription.setText(city.getAttractions());
            editTitle.setText(city.getName());
            imagePath = city.getImagePath();
            if (imagePath != null && !imagePath.isEmpty())
                Glide.with(this)
                        .load(imagePath)
                        .skipMemoryCache(true)
                        .centerCrop()
                        .into(imageViewCity);
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(view -> deleteItem());
            spinner.setSelected(true);
            spinner.setSelection(city.getContinent());
        }
        db = AppDatabase.getDatabase(getApplication());
    }

    private void deleteItem() {
        if (city != null) {
            AppDatabase.databaseExecutor.execute(() -> {
                db.cityDao().delete(city);
                runOnUiThread(() -> {
                    Toast.makeText(this, "gelöscht", Toast.LENGTH_LONG).show();
                });
            });
        }
        finish();
    }

    private void saveItem() {
        if (city == null) {
            city = new City();
        }
        city.setName(editTitle.getText().toString().trim());
        city.setAttractions(editDescription.getText().toString().trim());
        try {
            Number number = NumberFormat.getInstance().parse(editInhabitants.getText().toString());
            city.setInhabitants(number.intValue());
        } catch (NumberFormatException | ParseException ex) {
            editInhabitants.setError("leider keine ganze Zahl");
            return;
        }
        city.setContinent(spinner.getSelectedItemPosition());
        if (imageBitmap != null)
            city.setImagePath(copyImageToInternalStorage());
        if (city.getUid() > 0)
            AppDatabase.databaseExecutor.execute(() -> {
                db.cityDao().update(city);
            });
        else
            AppDatabase.databaseExecutor.execute(() -> {
                db.cityDao().insert(city);
            });

        finish();
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                Glide.with(this)
                        .asBitmap()
                        .load(selectedImageUri)
                        .centerCrop()
                        .into(
                                new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        imageViewCity.setImageBitmap(resource);
                                        imageBitmap = resource;
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }


                                });
            }
        }


    }

    private String copyImageToInternalStorage() {
        if (imageBitmap == null)
            return "";
        Date now = new Date();
        String imageFilename = now.getTime() + ".jpg";
        FileOutputStream fileOutputStream = null;
        File file = new File(this.getFilesDir(), imageFilename);
        try {
            fileOutputStream = this.openFileOutput(imageFilename, Context.MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

