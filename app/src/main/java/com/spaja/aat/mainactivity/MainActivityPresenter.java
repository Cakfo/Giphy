package com.spaja.aat.mainactivity;

import com.spaja.aat.model.ApiResponse;
import com.spaja.aat.networking.GiphyAPI;
import com.spaja.aat.repo.RepositoryImpl;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Spaja on 26-Oct-17.
 */

class MainActivityPresenter {

    private MainActivityView view;
    private RepositoryImpl repository;
    private CompositeDisposable disposable;

    MainActivityPresenter(MainActivityView view, RepositoryImpl repository) {
        this.view = view;
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    void getGifs(final String inputText) {

        view.loadRecyclerView(inputText);
        if (inputText.length() != 0) {

            Observable<ApiResponse> gifsObservable = GiphyAPI.service.getGifsReact(GiphyAPI.API_KEY, inputText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());

            DisposableObserver<ApiResponse> observer = new DisposableObserver<ApiResponse>() {
                @Override
                public void onNext(ApiResponse response) {
                    repository.saveToDB(response.getGifData());
                }

                @Override
                public void onError(Throwable e) {
                    view.showErrorToast();
                }

                @Override
                public void onComplete() {

                }
            };

            gifsObservable.subscribe(observer);
            disposable.add(observer);

        }
//        if (inputText.length() != 0) {
//            GiphyAPI.service.getGifs(GiphyAPI.API_KEY, inputText.toString()).enqueue(new Callback<ApiResponse>() {
//                @Override
//                public void onResponse(Call<ApiResponse> call, final Response<ApiResponse> response) {
//
//                    repository.saveToDB(response.body().getGifData());
//
//                }
//
//                @Override
//                public void onFailure(Call<ApiResponse> call, Throwable t) {
//                }
//            });
//        }
    }

    void clearResources() {
        disposable.clear();
    }
}
