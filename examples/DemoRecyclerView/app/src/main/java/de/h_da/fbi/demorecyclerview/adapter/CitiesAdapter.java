package de.h_da.fbi.demorecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.h_da.fbi.demorecyclerview.R;
import de.h_da.fbi.demorecyclerview.model.City;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>{

    private final List<City> cities;
    private final Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CitiesAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        City city = cities.get(position);
        holder.textViewName.setText(city.getName());
        Glide.with(context).load(city.getImageUrl()).into(holder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cities.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class CitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView textViewName;
        public final ImageView imageView;
        public CitiesViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            City clickedCity = cities.get(getAdapterPosition());
            Toast.makeText(context, clickedCity.getName(), Toast.LENGTH_SHORT).show();
        }
    }



    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CitiesAdapter.CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cities, parent, false);
        return new CitiesViewHolder(view);
    }
}
