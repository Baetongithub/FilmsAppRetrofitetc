package com.geektech.filmsappretrofitetc.utils;

import android.app.Application;

import androidx.room.Room;

import com.geektech.filmsappretrofitetc.room.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class,
                "database").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
