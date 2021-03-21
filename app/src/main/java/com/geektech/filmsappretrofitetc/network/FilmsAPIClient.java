package com.geektech.filmsappretrofitetc.network;

import com.geektech.filmsappretrofitetc.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsAPIClient {

    public static FilmsAPIClient apiClient;
    public static Retrofit retrofit;

    public FilmsAPIClient() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized FilmsAPIClient getInstance() {
        if (apiClient == null) {
            apiClient = new FilmsAPIClient();
        }
        return apiClient;
    }

    public FilmsAPI getAPI() {
        return retrofit.create(FilmsAPI.class);
    }
}
