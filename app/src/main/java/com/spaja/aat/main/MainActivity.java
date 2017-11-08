package com.spaja.aat.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.spaja.aat.R;
import com.spaja.aat.model.GifData;
import com.spaja.aat.repo.RepositoryImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView (R.id.recycler) public RecyclerView recyclerView;
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

        EditText editText = (EditText) findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.getGifs(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void loadRecyclerView(CharSequence inputText) {
        if (inputText.length() != 0) {
            RealmResults<GifData> gifData = realm.where(GifData.class).contains("title", String.valueOf(inputText)).findAll();
            recyclerView.setAdapter(new MyRecyclerViewAdapter(MainActivity.this, gifData, true));
            recyclerView.setVisibility(android.view.View.VISIBLE);
        } else {
            recyclerView.setVisibility(android.view.View.GONE);
        }
    }
}
