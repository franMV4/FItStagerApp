package com.svalero.fitstagerapplication.api;



import static com.svalero.fitstagerapplication.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FitStagerApi {

    public static FitStagerApiInterface buildInstance() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FitStagerApiInterface.class);
    }
}