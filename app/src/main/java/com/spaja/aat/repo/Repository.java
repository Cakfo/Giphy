package com.spaja.aat.repo;

import com.spaja.aat.model.GifData;

/**
 * Created by Spaja on 26-Oct-17.
 */

public interface Repository {

    void saveOrUpdateToDB(GifData gifData);

}
