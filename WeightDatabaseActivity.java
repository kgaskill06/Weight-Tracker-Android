package com.example.weighttracker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.weighttracker.WeightActivity;

/*
Abstract class for DAO room database and SQLite - information on zybooks 5.10
 */

/*
https://guides.codepath.com/android/local-databases-with-sqliteopenhelper for referencing SQLite
 */
@Database(entities = {User.class, WeightEntity.class}, version = 1)
public abstract class WeightDatabaseActivity extends RoomDatabase {

    // assigns database name to variable, used with singleton pattern
    private static final String DATABASE_NAME = "weightTracker.db";

    //Assigns class reference to mWeightDatabaseActivity used with Singleton
    private static WeightDatabaseActivity mWeightDatabaseActivity;

    public static WeightDatabaseActivity getInstance(Context context) {
        if (mWeightDatabaseActivity == null) {
            mWeightDatabaseActivity = Room.databaseBuilder(context, WeightDatabaseActivity.class,
                    DATABASE_NAME).allowMainThreadQueries().build();
        }
        return mWeightDatabaseActivity;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public abstract UserDAO userDao();

    /*
    https://developer.android.com/training/data-storage/room for reference on local storage and
    Data Access Object (DAO). and chapter 5 of zybooks 5.10
     */

}
