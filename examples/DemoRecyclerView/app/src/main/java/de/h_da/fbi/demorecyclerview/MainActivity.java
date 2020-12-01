package de.h_da.fbi.demorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import de.h_da.fbi.demorecyclerview.adapter.CitiesAdapter;
import de.h_da.fbi.demorecyclerview.model.City;

public class MainActivity extends AppCompatActivity {

    private final List<City> cities = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniCities();

        RecyclerView recyclerViewCities = findViewById(R.id.recyclerViewCities);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        recyclerViewCities.setLayoutManager(layoutManager);
        recyclerViewCities.setAdapter(new CitiesAdapter(this, cities));
    }

    private void iniCities(){
        cities.add(new City("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Paris_-_Eiffelturm_und_Marsfeld2.jpg/500px-Paris_-_Eiffelturm_und_Marsfeld2.jpg", "Paris"));
        cities.add(new City("https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Melbourne_montage_2019.jpg/540px-Melbourne_montage_2019.jpg", "Melbourne"));
        cities.add(new City("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Luisenplatz%2C_Darmstadt.jpg/500px-Luisenplatz%2C_Darmstadt.jpg", "Darmstadt"));
    }
}