package com.spaja.aat.repo;

import com.spaja.aat.model.GifData;

import io.realm.RealmList;

/**
 * Created by Spaja on 26-Oct-17.
 */

public interface Repository {

    void saveOrUpdateToDB(RealmList<GifData> gifData);

}
