package com.geektech.filmsappretrofitetc.ui.home;

import android.graphics.Bitmap;
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
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.adapters.HomeAdapter;
import com.geektech.filmsappretrofitetc.databinding.FragmentHomeBinding;
import com.geektech.filmsappretrofitetc.databinding.LayoutBottomSheetDialogBinding;
import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.network.FilmsAPIClient;
import com.geektech.filmsappretrofitetc.utils.Constants;
import com.geektech.filmsappretrofitetc.utils.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter;
    private List<AllFilms> getAllFilms;
    private FragmentHomeBinding ui;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ui = FragmentHomeBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllFilms = new ArrayList<>();
        recyclerOnScrollListener();

        //todo 8th Home Work
        ui.fabScan.setOnClickListener(v -> {
            IntentIntegrator integrator = new IntentIntegrator(requireActivity());
            integrator.initiateScan();
        });

        Call<List<AllFilms>> call = FilmsAPIClient.getInstance().getAPI().getAllFilms();
        call.enqueue(new Callback<List<AllFilms>>() {
            @Override
            public void onResponse(@NonNull Call<List<AllFilms>> call, @NonNull Response<List<AllFilms>> response) {
                if (response.body() != null) {
                    getAllFilms = response.body();
                    homeAdapter = new HomeAdapter(getAllFilms);
                    ui.recyclerViewFilms.setAdapter(homeAdapter);
                    homeAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            AllFilms allFilms = homeAdapter.getItem(position);
                            openDetailedFilms(allFilms);
                        }

                        //todo 8th Home Work
                        @Override
                        public void onImageClick(int position) {
                            AllFilms allFilms = homeAdapter.getItem(position);
                            openBottomSheet(allFilms);
                        }
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

    //todo 8th Home Work
    private void openBottomSheet(AllFilms allFilms) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
        LayoutBottomSheetDialogBinding dialogBinding = LayoutBottomSheetDialogBinding.inflate(getLayoutInflater());

        BarcodeEncoder encoder = new BarcodeEncoder();

        try {
            Bitmap bitmap = encoder.encodeBitmap(allFilms.getID(),
                    BarcodeFormat.QR_CODE, 300, 300);
            dialogBinding.imageQrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Toast.makeText(requireContext(), R.string.something_went_wrong +
                    e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        dialogBinding.textCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(dialogBinding.getRoot());
        bottomSheetDialog.show();
    }

    private void openDetailedFilms(AllFilms allFilms) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.ID, allFilms.getID());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.detailedFilmsFragment, bundle);
    }

    private void recyclerOnScrollListener() {
        ui.recyclerViewFilms.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    if (ui.fabScan.isShown() && ui.scanText.isShown()) {
                        ui.fabScan.hide();
                        ui.scanText.setVisibility(View.GONE);
                    }
                } else if (dy < 0) {
                    if (!ui.fabScan.isShown() && !ui.scanText.isShown()) {
                        ui.fabScan.show();
                        ui.scanText.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}