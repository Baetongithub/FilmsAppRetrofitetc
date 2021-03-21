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
import com.geektech.filmsappretrofitetc.adapters.LocationAdapter;
import com.geektech.filmsappretrofitetc.databinding.FragmentLocationBinding;
import com.geektech.filmsappretrofitetc.models.Location;
import com.geektech.filmsappretrofitetc.network.FilmsAPI;
import com.geektech.filmsappretrofitetc.network.FilmsAPIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFragment extends Fragment {

    private FragmentLocationBinding locationBinding;
    private List<Location> locationList;
    private LocationAdapter locationAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        locationBinding = FragmentLocationBinding.inflate(inflater, container, false);
        return locationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationList = new ArrayList<>();

        Call<List<Location>> call = FilmsAPIClient.getInstance().getAPI().getLocation();

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(@NonNull Call<List<Location>> call, @NonNull Response<List<Location>> response) {
                if (response.body() != null) {
                    locationList = response.body();
                    locationAdapter = new LocationAdapter(locationList);
                    locationBinding.recyclerViewLocation.setAdapter(locationAdapter);
                } else {
                    Toast.makeText(requireContext(), R.string.something_went_wrong + response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Location>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), R.string.something_went_wrong + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}