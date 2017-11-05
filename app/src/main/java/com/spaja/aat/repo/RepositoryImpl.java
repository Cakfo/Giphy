package com.spaja.aat.repo;

import com.spaja.aat.model.GifData;

import io.realm.Realm;

/**
 * Created by Spaja on 26-Oct-17.
 */

public class RepositoryImpl implements Repository {

    private Realm realm;

    public RepositoryImpl() {
        realm = Realm.getDefaultInstance();
    }

    public void saveOrUpdateToDB(final GifData gifData) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(gifData);
            }
        });
    }
}
