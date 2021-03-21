package com.geektech.filmsappretrofitetc.ui.my_films;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyFilmsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyFilmsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}