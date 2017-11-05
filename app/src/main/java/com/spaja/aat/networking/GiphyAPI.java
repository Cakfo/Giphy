package com.spaja.aat.networking;

import com.spaja.aat.model.ApiResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Spaja on 03-Nov-17.
 */

public interface GiphyAPI {

    String BASE_URL = "http://api.giphy.com/";

    String API_KEY = "yXeFI6D8TyVNtjLVYv17riJr0Or4kp23";

    @GET ("/v1/gifs/search")
    Call<ApiResponse> getGifs(@Query ("api_key") String apiKey, @Query ("q") String query);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GiphyAPI service = retrofit.create(GiphyAPI.class);
}
