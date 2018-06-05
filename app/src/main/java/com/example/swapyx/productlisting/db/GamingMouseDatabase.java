package com.example.swapyx.productlisting.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.Executors;

/**
 * The Room Database that contains the GamingMouse table.
 */

@Database(entities = {GamingMouse.class}, version = 1, exportSchema = false)
public abstract class GamingMouseDatabase extends RoomDatabase {

    private static GamingMouseDatabase INSTANCE;

    public static final String DATABASE_NAME = "product-list-db";

    public abstract GamingMouseDao gamingMouseDao();

    public static GamingMouseDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (GamingMouseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(), GamingMouseDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
