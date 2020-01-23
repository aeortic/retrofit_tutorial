package com.example.retrofit_tutorial.network;

import com.example.retrofit_tutorial.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPhotoService {

    @GET("/photo")
    Call<List<RetroPhoto>>  getAllPhotos();
}
