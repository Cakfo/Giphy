package com.spaja.aat.helper;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Spaja on 03-Nov-17.
 */

public class Controller extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
