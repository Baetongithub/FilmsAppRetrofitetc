package com.geektech.filmsappretrofitetc.network;

import com.geektech.filmsappretrofitetc.models.AllFilms;
import com.geektech.filmsappretrofitetc.models.Location;
import com.geektech.filmsappretrofitetc.models.People;
import com.geektech.filmsappretrofitetc.models.Species;
import com.geektech.filmsappretrofitetc.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmsAPI {

    @GET(Constants.FILM)
    Call<List<AllFilms>> getAllFilms();

    @GET(Constants.FILM_BY_ID)
    Call<AllFilms> getFilmsByID(
            @Path(Constants.ID) String id
    );

    @GET(Constants.PEOPLE)
    Call<List<People>> getPeople();

    @GET(Constants.SPECIES)
    Call<List<Species>> getSpecies();

    @GET(Constants.LOCATIONS)
    Call<List<Location>> getLocation();

}
