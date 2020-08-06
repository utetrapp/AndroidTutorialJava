package de.h_da.fbi.demoroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import de.h_da.fbi.demoroom.model.City;

import java.util.List;

//@see
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {
    private List<City> cities;
    private Context context;

    public CitiesAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.viewholder_city, parent, false);
        return new CitiesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        City city = cities.get(position);
        holder.textViewTitle.setText(String.format("%s (%s)", city.getName(), city.getContinentAsEnumField().toString()));
        holder.textViewInhabitants.setText(String.format("%d Einwohner*innen", city.getInhabitants()));
        holder.textViewDescription.setText(city.getAttractions());
        Glide.with(context).load(city.getImagePath()).fitCenter().into(holder.imageViewCity);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (cities != null)
            return cities.size();
        return 0;
    }

    void setCities(List<City> cities){
        this.cities = cities;
        notifyDataSetChanged();
    }

    class CitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewInhabitants, textViewDescription;
        ImageView imageViewCity;

        public CitiesViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewInhabitants = itemView.findViewById(R.id.textViewInhabitants);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewCity = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            City city = cities.get(getAdapterPosition());

            Intent intent = new Intent(context, CityDetailsActivity.class);
            intent.putExtra("city", city);

            ((Activity) context).startActivity(intent);
        }

    }
}

