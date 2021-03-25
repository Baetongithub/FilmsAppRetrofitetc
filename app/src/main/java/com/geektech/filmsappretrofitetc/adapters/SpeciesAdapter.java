package com.geektech.filmsappretrofitetc.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.ItemSpeciesBinding;
import com.geektech.filmsappretrofitetc.models.Species;
import com.geektech.filmsappretrofitetc.utils.Constants;

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

        @SuppressLint("SetTextI18n")
        public void onBind(Species species) {
            itemSpeciesBinding.nameSpecies.setText(Constants.NAME + species.getName());
            itemSpeciesBinding.classificationSpecies.setText(Constants.CLASSIFICATION + species.getClassification());
            itemSpeciesBinding.eyeColorsSpecies.setText(Constants.EYE_COLOR + species.getEye_colors());
            itemSpeciesBinding.hairColorsSpecies.setText(Constants.HAIR_COLOR + species.getHair_colors());
        }
    }
}