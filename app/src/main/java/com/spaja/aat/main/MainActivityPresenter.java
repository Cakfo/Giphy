package com.spaja.aat.main;

import com.spaja.aat.model.ApiResponse;
import com.spaja.aat.model.GifData;
import com.spaja.aat.networking.GiphyAPI;
import com.spaja.aat.repo.RepositoryImpl;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Spaja on 26-Oct-17.
 */

class MainActivityPresenter {

    private MainActivityView view;
    private RepositoryImpl repository;

    MainActivityPresenter(MainActivityView view, RepositoryImpl repository) {
        this.view = view;
        this.repository = repository;
    }

    void getGifs(final String inputText) {
        view.loadRecyclerView(inputText);
        if (inputText.length() != 0) {
            GiphyAPI.service.getGifs(GiphyAPI.API_KEY, inputText).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, final Response<ApiResponse> response) {
                    RealmList<GifData> gifData = response.body().getGifData();
                    for (int i = 0; i < gifData.size(); i++) {
                        repository.saveOrUpdateToDB(gifData.get(i));
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                }
            });
        }
    }
}