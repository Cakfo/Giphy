package com.spaja.aat.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Spaja on 11-Nov-17.
 */

public class SaveAndShareHelper {

    private static void saveGifToCache(Context context, File gif) {

        File cachePath = new File(context.getCacheDir(), "images");

        if (!cachePath.exists()) {
            cachePath.mkdirs();
        }

        File file = new File(cachePath + "/gif.gif");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            bis = new BufferedInputStream(new FileInputStream(gif));
            bos = new BufferedOutputStream(new FileOutputStream(file, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveGifToGallery(Context context, Uri gif) {

        String sourceFile = gif.getPath();

        File imagePath = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES) + File.separator + "AAT" + File.separator);

        if (!imagePath.exists()) {
            imagePath.mkdir();
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        File file = new File(imagePath, "Gif_" + System.currentTimeMillis() + ".gif");
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
            bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bos = new BufferedOutputStream(new FileOutputStream(file, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanMedia(context, file);
    }

    private static void scanMedia(Context context, File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        context.getApplicationContext().sendBroadcast(mediaScanIntent);
    }

    static void shareGif(Context context, File file) {

        saveGifToCache(context, file);

        File imagePath = new File(context.getCacheDir(), "images");

        File newFile = new File(imagePath, "gif.gif");

        Uri contentUri = FileProvider.getUriForFile(context,
                "com.spaja.aat.fileprovider", newFile);

        if (contentUri != null) {

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app:"));
        }
    }

    public static Single<File> downloadImage(final Context context, final String url) {

        return Single.just(1)
                .subscribeOn(Schedulers.io())
                .map(new Function<Integer, File>() {
                    @Override
                    public File apply(Integer integer) throws Exception {
                        return Glide.with(context)
                                .downloadOnly()
                                .load(url)
                                .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                .get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}

