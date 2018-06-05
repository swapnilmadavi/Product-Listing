package com.example.swapyx.productlisting.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GamingMouseDao {

    /**
     * Insert a list of products in the database. If the product already exists, replace it.
     *
     * @param products the list of products to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GamingMouse> products);

    /**
     * Select all products from the Gaming Mouse table.
     *
     * @return all products.
     */
    @Query("SELECT * FROM GamingMouse")
    List<GamingMouse> getAllProducts();
}
