package com.geektech.filmsappretrofitetc.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.geektech.filmsappretrofitetc.models.AllFilms;

@Database(entities = {AllFilms.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FilmsDao filmsDao();
}
