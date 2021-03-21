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
import com.geektech.filmsappretrofitetc.adapters.PeopleAdapter;
import com.geektech.filmsappretrofitetc.databinding.FragmentPeopleBinding;
import com.geektech.filmsappretrofitetc.models.Location;
import com.geektech.filmsappretrofitetc.models.People;
import com.geektech.filmsappretrofitetc.network.FilmsAPI;
import com.geektech.filmsappretrofitetc.network.FilmsAPIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleFragment extends Fragment {

    private FragmentPeopleBinding peopleBinding;
    private List<People> peopleList;
    private PeopleAdapter peopleAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        peopleBinding = FragmentPeopleBinding.inflate(inflater, container, false);
        return peopleBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        peopleList = new ArrayList<>();

        Call<List<People>> call = FilmsAPIClient.getInstance().getAPI().getPeople();

        call.enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(@NonNull Call<List<People>> call, @NonNull Response<List<People>> response) {
                if (response.body() != null) {
                    peopleList = response.body();
                    peopleAdapter = new PeopleAdapter(peopleList);
                    peopleBinding.recyclerViewPeople.setAdapter(peopleAdapter);
                } else {
                    Toast.makeText(requireContext(), R.string.something_went_wrong + response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<People>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), R.string.something_went_wrong + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}