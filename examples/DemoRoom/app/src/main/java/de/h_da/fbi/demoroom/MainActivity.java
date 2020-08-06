package de.h_da.fbi.demoroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.h_da.fbi.demoroom.model.AppDatabase;
import de.h_da.fbi.demoroom.model.City;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener { //click auf Spinner

    private List<City> allCities;
    private RecyclerView recyclerView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvCities);
        //Layout für die Anordnung der Elemente
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        spinner = findViewById(R.id.spinnerContinents);
        //vordefiniert existiert: android.R.layout.simple_spinner_item
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, City.Continent.values()));
        spinner.setOnItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CityDetailsActivity.class);
            startActivity(intent);
        });
    }

    private void fillCities(final City.Continent continent) {
        List<City> filteredCities;
        if (continent == City.Continent.Any)
            filteredCities = allCities;
        else {
            filteredCities = new ArrayList<>();
            //diese Syntax braucht api 24, also Android 7, alternativ einfach
            //for (City city : allCities)
            //                if (city.getContinent() == continent)
            //                    filteredCities.add(city);
            allCities.stream().filter(c -> c.getContinentAsEnumField() == continent).forEachOrdered(filteredCities::add);
        }

        recyclerView.setAdapter(new CitiesAdapter(this, filteredCities));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        fillCities(City.Continent.values()[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    @Override
    protected void onResume() {
        super.onResume();
        spinner.setSelected(false);
        AppDatabase.databaseExecutor.execute(() -> {
            List<City> cities = AppDatabase.getDatabase(getApplication()).cityDao().getAll();
            runOnUiThread(() -> {
                allCities = cities;
                fillCities(City.Continent.Any);
            });
        });

    }

}
