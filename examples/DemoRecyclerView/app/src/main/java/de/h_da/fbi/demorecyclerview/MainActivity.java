package de.h_da.fbi.demorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.h_da.fbi.demorecyclerview.adapter.CitiesAdapter;
import de.h_da.fbi.demorecyclerview.model.City;

public class MainActivity extends AppCompatActivity {
    private CitiesAdapter adapter;
    private final List<City> cities = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniCities();

        RecyclerView recyclerViewCities = findViewById(R.id.recyclerViewCities);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewCities.setLayoutManager(layoutManager);
        adapter = new CitiesAdapter(this, cities);
        recyclerViewCities.setAdapter(adapter);

        ItemTouchHelper helper = getItemTouchHelper();

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(recyclerViewCities);
    }

    private ItemTouchHelper getItemTouchHelper() {
        return new ItemTouchHelper(new ItemTouchHelper
                    .SimpleCallback(
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                            ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                /**
                 * Defines the drag and drop functionality.
                 *
                 * @param recyclerView The RecyclerView that contains the list items
                 * @param viewHolder The SportsViewHolder that is being moved
                 * @param target The SportsViewHolder that you are switching the
                 *               original one with.
                 * @return true if the item was moved, false otherwise
                 */
                @Override
                public boolean onMove(RecyclerView recyclerView,
                                      RecyclerView.ViewHolder viewHolder,
                                      RecyclerView.ViewHolder target) {
                    // Get the from and to positions.
                    int from = viewHolder.getAdapterPosition();
                    int to = target.getAdapterPosition();

                    // Swap the items and notify the adapter.
                    Collections.swap(cities, from, to);
                    adapter.notifyItemMoved(from, to);
                    return true;
                }

                /**
                 * Defines the swipe to dismiss functionality.
                 *
                 * @param viewHolder The viewholder being swiped.
                 * @param direction The direction it is swiped in.
                 */
                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                     int direction) {
                    // Remove the item from the dataset.
                    cities.remove(viewHolder.getAdapterPosition());
                    // Notify the adapter.
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            });
    }

    private void iniCities(){
        cities.add(new City("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Paris_-_Eiffelturm_und_Marsfeld2.jpg/500px-Paris_-_Eiffelturm_und_Marsfeld2.jpg", "Paris"));
        cities.add(new City("https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Melbourne_montage_2019.jpg/540px-Melbourne_montage_2019.jpg", "Melbourne"));
        cities.add(new City("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Luisenplatz%2C_Darmstadt.jpg/500px-Luisenplatz%2C_Darmstadt.jpg", "Darmstadt"));
    }
}