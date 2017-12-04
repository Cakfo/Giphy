package com.spaja.aat.gifactivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.spaja.aat.R;
import com.spaja.aat.helper.SaveAndShareHelper;
import com.spaja.aat.helper.SaveTask;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class GifActivity extends AppCompatActivity implements GifActivityView {

    @BindView (R.id.gif) ImageView imageView;
    @BindView (R.id.save_gif) LinearLayout saveGif;
    @BindView (R.id.share_gif) LinearLayout shareGif;
    @BindView (R.id.gif_title) TextView gifTitle;
    private GifActivityPresenter presenter;
    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        presenter = new GifActivityPresenter(this);

        gifTitle.setText(title);

        displayGif(url);

        saveGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGif(url);
            }
        });

        shareGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveTask(GifActivity.this, true).execute(url);

            }
        });
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


    void saveGif(String url) {

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    99);
        } else {
            if (url != null) {
//                new SaveTask(this, false).execute(url);
                Single<File> fileSingle = SaveAndShareHelper.downloadImage(GifActivity.this, url);

                fileSingle.subscribe(new SingleObserver<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        int i = 0;
                    }

                    @Override
                    public void onSuccess(File file) {
                        Uri uri = Uri.parse(file.toString());
                        SaveAndShareHelper.saveGifToGallery(GifActivity.this, uri);
                        Toast.makeText(GifActivity.this, "Gif saved to gallery.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        int i = 0;
                    }
                });
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == 99 && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveGif.performClick();
        } else {
            ActivityCompat.requestPermissions(GifActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    99);
        }
    }
}
