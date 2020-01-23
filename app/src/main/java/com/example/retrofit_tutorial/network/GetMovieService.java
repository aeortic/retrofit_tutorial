package com.example.retrofit_tutorial.network;

import com.example.retrofit_tutorial.model.MovieDatabase;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMovieService {
    @GET("/database_small")
    Call<MovieDatabase> getSmallDataSet();
}
