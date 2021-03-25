package com.geektech.filmsappretrofitetc.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.filmsappretrofitetc.R;
import com.geektech.filmsappretrofitetc.databinding.ItemPeopleBinding;
import com.geektech.filmsappretrofitetc.models.People;
import com.geektech.filmsappretrofitetc.utils.Constants;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private final List<People> peopleList;

    public PeopleAdapter(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.onBind(peopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public static class PeopleViewHolder extends RecyclerView.ViewHolder {

        private final ItemPeopleBinding itemPeopleBinding;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPeopleBinding = ItemPeopleBinding.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        private void onBind(People people) {
            itemPeopleBinding.namePeople.setText(Constants.NAME + people.getName());
            itemPeopleBinding.agePeople.setText(Constants.AGE + people.getAge());
            itemPeopleBinding.genderPeople.setText(Constants.GENDER + people.getGender());
            itemPeopleBinding.hairColorPeople.setText(Constants.HAIR_COLOR + people.getHair_color());
            itemPeopleBinding.eyeColorPeople.setText(Constants.EYE_COLOR + people.getEye_color());
        }
    }
}
