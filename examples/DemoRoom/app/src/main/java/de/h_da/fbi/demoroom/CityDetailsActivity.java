package de.h_da.fbi.demoroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import de.h_da.fbi.demoroom.model.AppDatabase;
import de.h_da.fbi.demoroom.model.City;
import de.h_da.fbi.demoroom.model.DatabaseClient;

public class CityDetailsActivity extends AppCompatActivity {
    private City city = null;
    AppDatabase db;
    private EditText editTitle, editDescription, editInhabitants;
    private ImageView imageViewCity;
    private Spinner spinner;
    private static final int REQUEST_PICK_IMAGE = 42;
    private String imageUri = "";
    private int takeFlags;
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
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTitle = findViewById(R.id.editTitle);
        editInhabitants = findViewById(R.id.editInhabitants);
        editDescription = findViewById(R.id.editDescription);


        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setVisibility(View.INVISIBLE);
        Button btnUpdate = findViewById(R.id.btnSave);
        btnUpdate.setOnClickListener(view -> saveItem());
        spinner = findViewById(R.id.spinnerContinents);
        spinner.setAdapter(new ArrayAdapter<City.Continent>(this, R.layout.spinner_item, City.Continent.values()));
        spinner.setSelected(false);
        imageViewCity = findViewById(R.id.imageView);
        imageViewCity.setOnClickListener(view -> selectImage());

        city = (City) getIntent().getSerializableExtra("city");
        if (city != null) {
            editInhabitants.setText(String.format("%,d", city.getInhabitants()));
            editDescription.setText(city.getAttractions());
            editTitle.setText(city.getName());
            imageUri = city.getImageUri();
            if (imageUri != null && !imageUri.isEmpty())
                Glide.with(this).load(imageUri).fitCenter().into(imageViewCity);
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(view -> deleteItem());
            spinner.setSelected(true);
            spinner.setSelection(city.getContinent());
        }
        db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();
    }

    private void deleteItem() {
        if (city != null) {
            db.cityDao().delete(city);
            Toast.makeText(this, "gelöscht", Toast.LENGTH_LONG).show();
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
            city.setInhabitants(Integer.parseInt(editInhabitants.getText().toString()));
        } catch (NumberFormatException ex) {
            editInhabitants.setError("leider keine ganze Zahl");
            return;
        }
        city.setContinent(spinner.getSelectedItemPosition());
        city.setImageUri(imageUri);
        if (city.getUid() > 0)
            db.cityDao().update(city);
        else
            db.cityDao().insert(city);
        finish();
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        takeFlags = intent.getFlags()
                & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PICK_IMAGE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                //sonst nach Neustart nicht mehr verfügbar ...

                getContentResolver().takePersistableUriPermission(uri, takeFlags);

                //getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageViewCity.setImageURI(uri);
                imageUri = uri.toString();
            }
        }


    }
}

