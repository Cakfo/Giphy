package com.spaja.aat.helper;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Spaja on 07-Nov-17.
 */

public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeFace(Context context, String fontName) {

        Typeface typeface = fontCache.get(fontName);

        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), fontName);
            fontCache.put(fontName, typeface);
        }
        return typeface;
    }
}
