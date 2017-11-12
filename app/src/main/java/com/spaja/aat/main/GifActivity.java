package com.spaja.aat.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.spaja.aat.R;
import com.spaja.aat.helper.SaveTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GifActivity extends AppCompatActivity {

    @BindView (R.id.gif) ImageView imageView;
    @BindView (R.id.progress_bar) ProgressBar progressBar;
    @BindView (R.id.save_image) Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        ButterKnife.bind(this);

        final String url = getIntent().getStringExtra("url");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadGif(url);
            }
        });

        displayGif(url);

    }

    private void displayGif(String url) {
        if (url != null) {
            Glide.with(this)
                    .asGif()
                    .load(url)
                    .listener(new RequestListener<GifDrawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                            findViewById(R.id.progress_bar).setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        }
    }

    private void downloadGif(String url) {

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    99);

        } else {
            new SaveTask(GifActivity.this.getApplicationContext()).execute(url);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == 99 && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            save.performClick();
        } else {
            ActivityCompat.requestPermissions(GifActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    99);
        }
    }
}
