package com.geektech.filmsappretrofitetc.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.ItemLocationBinding;
import com.geektech.filmsappretrofitetc.models.Location;
import com.geektech.filmsappretrofitetc.utils.Constants;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private final List<Location> allFilmsList;

    public LocationAdapter(List<Location> allFilmsList) {
        this.allFilmsList = allFilmsList;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.onBind(allFilmsList.get(position));
    }

    @Override
    public int getItemCount() {
        return allFilmsList.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {

        private final ItemLocationBinding itemLocationBinding;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLocationBinding = ItemLocationBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void onBind(Location location) {
            itemLocationBinding.nameLocation.setText(Constants.NAME + location.getName());
            itemLocationBinding.climateLocation.setText(Constants.CLIMATE + location.getClimate());
            itemLocationBinding.terrainLocation.setText(Constants.TERRAIN + location.getTerrain());
            itemLocationBinding.surfaceWaterLocation.setText(Constants.SURFACE_WATER + location.getSurface_water());
        }
    }
}
