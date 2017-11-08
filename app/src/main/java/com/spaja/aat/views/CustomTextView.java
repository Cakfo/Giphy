package com.spaja.aat.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.spaja.aat.helper.FontCache;

/**
 * Created by Spaja on 05-Nov-17.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
        setFont(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {

        setTypeface(FontCache.getTypeFace(context, "Fonts/robotoregular.ttf"));
    }


}
