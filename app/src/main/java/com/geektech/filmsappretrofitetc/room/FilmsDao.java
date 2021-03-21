package com.geektech.filmsappretrofitetc.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.geektech.filmsappretrofitetc.models.AllFilms;

import java.util.List;

@Dao
public interface FilmsDao {

    @Query("SELECT * FROM allfilms")
    List<AllFilms> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AllFilms allFilms);

    @Update
    void update(AllFilms allFilms);

    @Delete
    void delete(AllFilms allFilms);
}
