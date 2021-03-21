package com.geektech.filmsappretrofitetc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.ItemSpeciesBinding;
import com.geektech.filmsappretrofitetc.models.Species;

import java.util.List;

public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder> {

    private final List<Species> allFilmsList;

    public SpeciesAdapter(List<Species> allFilmsList) {
        this.allFilmsList = allFilmsList;
    }

    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_species, parent, false);
        return new SpeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesViewHolder holder, int position) {
        holder.onBind(allFilmsList.get(position));
    }

    @Override
    public int getItemCount() {
        return allFilmsList.size();
    }

    public static class SpeciesViewHolder extends RecyclerView.ViewHolder {

        private final ItemSpeciesBinding itemSpeciesBinding;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemSpeciesBinding = ItemSpeciesBinding.bind(itemView);
        }

        public void onBind(Species species) {
            itemSpeciesBinding.nameSpecies.setText(species.getName());
            itemSpeciesBinding.classificationSpecies.setText(species.getClassification());
            itemSpeciesBinding.eyeColorsSpecies.setText(species.getEye_colors());
            itemSpeciesBinding.hairColorsSpecies.setText(species.getHair_colors());
        }
    }
}