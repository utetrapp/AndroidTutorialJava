package de.h_da.fbi.demorecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {
    private List<City> cities;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CitiesAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class CitiesViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewName;
        public ImageView imageView;
        public CitiesViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }



    // Create new views (invoked by the layout manager)
    @Override
    public CitiesAdapter.CitiesViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_cities, parent, false);
        CitiesViewHolder vh = new CitiesViewHolder(view);
        return vh;
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

}


