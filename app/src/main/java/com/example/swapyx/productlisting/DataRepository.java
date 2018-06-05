package com.example.swapyx.productlisting;

import android.util.Log;

import com.example.swapyx.productlisting.db.DataGenerator;
import com.example.swapyx.productlisting.db.GamingMouse;
import com.example.swapyx.productlisting.db.GamingMouseDatabase;

import java.util.List;

/**
 * Repository handling the work with Gaming Mouses.
 */
public class DataRepository {
    private static DataRepository INSTANCE = null;
    private final GamingMouseDatabase mDatabase;
    private final AppExecutors mExecutors;

    private List<GamingMouse> cachedProducts;

    private DataRepository(GamingMouseDatabase database, AppExecutors executors, RepositoryListener listener) {
        mDatabase = database;
        mExecutors = executors;
        initializeDb(listener);
        Log.d("DataRepository", "INSTANCE created");
    }

    public static DataRepository getInstance(final GamingMouseDatabase database,
                                             AppExecutors executors,
                                             RepositoryListener listener) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(database, executors, listener);
                }
            }
        }
        return INSTANCE;
    }

    private void initializeDb(final RepositoryListener listener) {
        Log.d("DataRepository", "initializing db...");
        Runnable initDbRunnable = new Runnable() {
            @Override
            public void run() {
                mDatabase.gamingMouseDao().insertAll(DataGenerator.generateProducts());
                mExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("DataRepository", "db initialized!!");
                        if (listener != null){
                            listener.onDbInitialized();
                        }
                    }
                });
            }
        };

        mExecutors.diskIO().execute(initDbRunnable);
    }

    /**
     * Get the list of products from the database.
     */
    public void getAllProducts(final RepositoryListener listener) {
        Runnable getAllProductsRunnable = new Runnable() {
            @Override
            public void run() {
                final List<GamingMouse> productList = mDatabase.gamingMouseDao().getAllProducts();
                mExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null){
                            listener.onProductsFetched(productList);
                        }
                    }
                });
            }
        };

        mExecutors.diskIO().execute(getAllProductsRunnable);
    }


    public interface RepositoryListener{
        void onDbInitialized();
        void onProductsFetched(List<GamingMouse> productList);
    }
}
