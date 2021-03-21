package com.geektech.filmsappretrofitetc.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.FragmentDetailsFilmsBinding;
import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.network.FilmsAPIClient;
import com.geektech.filmsappretrofitetc.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFilmsFragment extends Fragment {

    private FragmentDetailsFilmsBinding detailsFilmsBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        detailsFilmsBinding = FragmentDetailsFilmsBinding.inflate(inflater, container, false);
        return detailsFilmsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailsFilmsBinding.textLocations.setOnClickListener(v -> openLocations());
        detailsFilmsBinding.textSpecies.setOnClickListener(v -> openSpecies());
        detailsFilmsBinding.textPeople.setOnClickListener(v -> openPeople());

        String id = (String) requireArguments().getSerializable(Constants.ID);

        Call<AllFilms> call = FilmsAPIClient.getInstance().getAPI().getFilmsByID(id);

        call.enqueue(new Callback<AllFilms>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<AllFilms> call, @NonNull Response<AllFilms> response) {
                if (response.body() != null) {
                    detailsFilmsBinding.titleDetails.setText(response.body().getTitle());
                    detailsFilmsBinding.directorDetails.setText(getString(R.string.director_) + response.body().getDirector());
                    detailsFilmsBinding.releaseDateDetails.setText(getString(R.string.release_date_) + response.body().getRelease_date());
                    detailsFilmsBinding.runningTimeDetails.setText(getString(R.string.running_time_) + response.body().getRunning_time());
                    detailsFilmsBinding.rtScoreDetails.setText(getString(R.string.rt_score_) + response.body().getRt_score());
                    detailsFilmsBinding.producerDetails.setText(getString(R.string.producer_) + response.body().getProducer());
                    detailsFilmsBinding.originalTitleRomanisedDetails.setText(getString(R.string.original_title_roman) + response.body().getOriginal_title_romanised());
                    detailsFilmsBinding.originalTitleDetails.setText(getString(R.string.original_title_) + response.body().getOriginal_title());
                    detailsFilmsBinding.descriptionDetails.setText(response.body().getDescription());
                } else {
                    Toast.makeText(requireContext(), R.string.something_went_wrong + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllFilms> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), R.string.something_went_wrong + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openPeople() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.peopleFragment);
    }

    private void openSpecies() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.speciesFragment);
    }

    private void openLocations() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.locationFragment);
    }
}