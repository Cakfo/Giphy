package com.spaja.aat.mainactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.spaja.aat.R;
import com.spaja.aat.model.GifData;
import com.spaja.aat.repo.RepositoryImpl;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView (R.id.recycler) public RecyclerView gifsRecyclerView;
    @BindView (R.id.search) EditText editText;
    private DisposableObserver<String> observer;
    private Realm realm;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        setupRecyclerView();

        presenter = new MainActivityPresenter(this, new RepositoryImpl());
        
        setupSearchView(editText);

    }

    private void setupSearchView(EditText editText) {
        Observable<String> string = RxTextView.textChanges(editText)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                })
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());

        observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                presenter.getGifs(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        string.subscribe(observer);
    }

    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        gifsRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void loadRecyclerView(CharSequence inputText) {
        if (inputText.length() != 0) {
            RealmResults<GifData> gifData = realm.where(GifData.class).contains("title", inputText.toString()).findAll();
            gifsRecyclerView.setAdapter(new GifsRecyclerViewAdapter(MainActivity.this, gifData, true));
            gifsRecyclerView.setVisibility(android.view.View.VISIBLE);
        } else {
            gifsRecyclerView.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(this, "YOU SHALL NOT PASS!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clearResources();
        observer.dispose();
    }
}
