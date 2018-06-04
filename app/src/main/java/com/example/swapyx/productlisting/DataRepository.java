package com.example.swapyx.productlisting;

import com.example.swapyx.productlisting.db.GamingMouseDatabase;

/**
 * Repository handling the work with Gaming Mouses.
 */
public class DataRepository {
    private static DataRepository INSTANCE = null;
    private final GamingMouseDatabase mDatabase;

    private DataRepository(GamingMouseDatabase database) {
        mDatabase = database;
    }

    public static DataRepository getInstance(final GamingMouseDatabase database) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(database);
                }
            }
        }
        return INSTANCE;
    }
}
