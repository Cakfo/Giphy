package com.spaja.aat.helper;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by Spaja on 11-Nov-17.
 */

public class SaveTask extends AsyncTask<String, Void, File> {

    private final Context context;

    public SaveTask(Context context) {
        this.context = context;
    }

    @Override
    protected File doInBackground(String... strings) {
        String url = strings[0];
        File gif = null;
        try {
            gif = Glide.with(context).downloadOnly().load(url).submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return gif;
    }

    @Override
    protected void onPostExecute(File gif) {
        if (gif != null) {
            Uri uri = Uri.parse(gif.toString());
            SaveHelper.saveGifToGallery(context, uri);
        }
    }
}
