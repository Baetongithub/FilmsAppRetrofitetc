package com.geektech.filmsappretrofitetc.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.adapters.SpeciesAdapter;
import com.geektech.filmsappretrofitetc.databinding.FragmentSpeciesBinding;
import com.geektech.filmsappretrofitetc.models.Species;
import com.geektech.filmsappretrofitetc.network.FilmsAPIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeciesFragment extends Fragment {

    private FragmentSpeciesBinding speciesBinding;
    private List<Species> speciesList;
    private SpeciesAdapter speciesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        speciesBinding = FragmentSpeciesBinding.inflate(inflater, container, false);
        return speciesBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        speciesList = new ArrayList<>();

        Call<List<Species>> call = FilmsAPIClient.getInstance().getAPI().getSpecies();

        call.enqueue(new Callback<List<Species>>() {
            @Override
            public void onResponse(@NonNull Call<List<Species>> call, @NonNull Response<List<Species>> response) {
                if (response.body() != null) {
                    speciesList = response.body();
                    speciesAdapter = new SpeciesAdapter(speciesList);
                    speciesBinding.recyclerViewSpecies.setAdapter(speciesAdapter);
                } else {
                    Toast.makeText(requireContext(), R.string.something_went_wrong + response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Species>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), R.string.something_went_wrong + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}