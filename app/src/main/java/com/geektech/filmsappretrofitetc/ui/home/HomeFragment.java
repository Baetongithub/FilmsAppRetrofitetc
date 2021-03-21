package com.geektech.filmsappretrofitetc.ui.home;

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
import com.geektech.filmsappretrofitetc.adapters.HomeAdapter;
import com.geektech.filmsappretrofitetc.databinding.FragmentHomeBinding;
import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.network.FilmsAPIClient;
import com.geektech.filmsappretrofitetc.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter;
    private List<AllFilms> getAllFilms;
    private FragmentHomeBinding homeBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllFilms = new ArrayList<>();

        Call<List<AllFilms>> call = FilmsAPIClient.getInstance().getAPI().getAllFilms();

        call.enqueue(new Callback<List<AllFilms>>() {
            @Override
            public void onResponse(@NonNull Call<List<AllFilms>> call, @NonNull Response<List<AllFilms>> response) {
                if (response.body() != null) {
                    getAllFilms = response.body();
                    homeAdapter = new HomeAdapter(getAllFilms);
                    homeBinding.recyclerViewFilms.setAdapter(homeAdapter);
                    homeAdapter.setOnItemClickListener(position -> {
                        AllFilms allFilms = homeAdapter.getItem(position);
                        openDetailsFilms(allFilms);
                    });
                } else {
                    Toast.makeText(requireContext(), R.string.please_check_internet_connection, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AllFilms>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), R.string.something_went_wrong + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetailsFilms(AllFilms allFilms) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ID, allFilms.getString());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.detailsFilmsFragment, bundle);
    }
}