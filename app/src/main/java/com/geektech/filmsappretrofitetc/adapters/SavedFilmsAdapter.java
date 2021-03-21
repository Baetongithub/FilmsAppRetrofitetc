package com.geektech.filmsappretrofitetc.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.ItemSavedFilmsBinding;
import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SavedFilmsAdapter extends RecyclerView.Adapter<SavedFilmsAdapter.ViewHolder> {

    private final List<AllFilms> allFilmsList;
    private OnItemClickListener onItemClickListener;

    public SavedFilmsAdapter() {
        this.allFilmsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_films, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(allFilmsList.get(position));
    }

    @Override
    public int getItemCount() {
        return allFilmsList.size();
    }

    public void setList(List<AllFilms> allFilmsList) {
        this.allFilmsList.clear();
        this.allFilmsList.addAll(allFilmsList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AllFilms getItem(int position) {
        return allFilmsList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemSavedFilmsBinding itemSavedFilmsBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemSavedFilmsBinding = ItemSavedFilmsBinding.bind(itemView);
            itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));
        }

        @SuppressLint("SetTextI18n")
        public void onBind(AllFilms allFilms) {
            itemSavedFilmsBinding.directorHome.setText(R.string.director_ + allFilms.getDirector());
            itemSavedFilmsBinding.titleHome.setText(allFilms.getTitle());
            itemSavedFilmsBinding.rtScoreHome.setText(allFilms.getRt_score());
            itemSavedFilmsBinding.runningTimeHome.setText(allFilms.getRunning_time());
            itemSavedFilmsBinding.releaseDateHome.setText(allFilms.getRelease_date());
        }
    }
}
