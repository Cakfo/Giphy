package com.spaja.aat.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.spaja.aat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GifActivity extends AppCompatActivity {

    private @BindView (R.id.gif) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");

        if (url != null) {
            Glide.with(this)
                    .asGif()
                    .load(url)
                    .into(imageView);
        }
    }
}
