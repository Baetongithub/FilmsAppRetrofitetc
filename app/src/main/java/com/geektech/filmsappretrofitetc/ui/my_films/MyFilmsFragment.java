package com.geektech.filmsappretrofitetc.ui.my_films;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.adapters.SavedFilmsAdapter;
import com.geektech.filmsappretrofitetc.databinding.FragmentMyFilmsBinding;
import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.utils.App;
import com.geektech.filmsappretrofitetc.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MyFilmsFragment extends Fragment {

    private FragmentMyFilmsBinding myFilmsBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        myFilmsBinding = FragmentMyFilmsBinding.inflate(inflater, container, false);
        return myFilmsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<AllFilms> allFilmsList = (ArrayList<AllFilms>) App.getAppDatabase().filmsDao().getAll();

        SavedFilmsAdapter filmsAdapter = new SavedFilmsAdapter();
        filmsAdapter.setList(allFilmsList);
        myFilmsBinding.recyclerViewFilms.setAdapter(filmsAdapter);
        filmsAdapter.setOnItemClickListener(position -> {
            AllFilms allFilms = filmsAdapter.getItem(position);
            openDetailsSavedFilms(allFilms);
        });
    }

    private void openDetailsSavedFilms(AllFilms allFilms) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ID, allFilms.getID());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.detailedFilmsFragment, bundle);
    }
}