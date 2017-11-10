package com.spaja.aat.repo;

import com.spaja.aat.model.GifData;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Spaja on 26-Oct-17.
 */

public class RepositoryImpl implements Repository {

    private Realm realm;

    public RepositoryImpl() {
        realm = Realm.getDefaultInstance();
    }

    public void saveOrUpdateToDB(final RealmList<GifData> gifData) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(gifData);
            }
        });
    }
}
