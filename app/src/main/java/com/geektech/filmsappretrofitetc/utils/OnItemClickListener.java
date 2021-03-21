package com.geektech.filmsappretrofitetc.utils;

public interface OnItemClickListener {
    void onClick(int position);
    default void onImageClick(int position){

    }
}
