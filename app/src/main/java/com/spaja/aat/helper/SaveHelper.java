package com.spaja.aat.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Spaja on 11-Nov-17.
 */

class SaveHelper {

    static void saveGifToGallery(Context context, Uri gif) {

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
}
