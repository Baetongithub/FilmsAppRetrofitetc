package com.geektech.filmsappretrofitetc.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.ItemHomeFilmsBinding;
import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.utils.App;
import com.geektech.filmsappretrofitetc.utils.Constants;
import com.geektech.filmsappretrofitetc.utils.OnItemClickListener;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final List<AllFilms> getAllFilms;
    private OnItemClickListener onItemClickListener;

    public HomeAdapter(List<AllFilms> getAllFilms) {
        this.getAllFilms = getAllFilms;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_films,
                parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBind(getAllFilms.get(position));
    }

    @Override
    public int getItemCount() {
        return getAllFilms.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AllFilms getItem(int position) {
        return getAllFilms.get(position);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        public ItemHomeFilmsBinding itemHomeFilmsBinding;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemHomeFilmsBinding = ItemHomeFilmsBinding.bind(itemView);
            itemHomeFilmsBinding.imageGetQrCode.setOnClickListener(v -> onItemClickListener.onImageClick(getAdapterPosition()));
            itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));
            itemHomeFilmsBinding.imageAddHome.setOnClickListener(v -> {
                AllFilms allFilms = getItem(getAdapterPosition());
                if (allFilms != null) {
                    App.getAppDatabase().filmsDao().insert(allFilms);
                    itemHomeFilmsBinding.imageAddHome.setImageResource(R.drawable.ic_round_bookmark_added_24);
                }
            });
        }

        @SuppressLint("SetTextI18n")
        private void onBind(AllFilms getAllFilms) {
            itemHomeFilmsBinding.titleHome.setText(getAllFilms.getTitle());
            itemHomeFilmsBinding.directorHome.setText(Constants.DIRECTOR +getAllFilms.getDirector());
            itemHomeFilmsBinding.rtScoreHome.setText(Constants.RT_SCORE +getAllFilms.getRt_score());
            itemHomeFilmsBinding.runningTimeHome.setText(Constants.RUNNING_TIME +getAllFilms.getRunning_time());
            itemHomeFilmsBinding.releaseDateHome.setText(Constants.RELEASE_DATE +getAllFilms.getRelease_date());
        }
    }
}
