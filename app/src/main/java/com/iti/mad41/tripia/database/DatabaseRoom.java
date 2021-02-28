package com.iti.mad41.tripia.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.iti.mad41.tripia.database.dao.LocalTripDao;
import com.iti.mad41.tripia.database.dto.LocalTrip;
import com.iti.mad41.tripia.database.dto.NoteConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@TypeConverters(NoteConverter.class)
@Database(entities = {LocalTrip.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {
    private static volatile DatabaseRoom INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract LocalTripDao tripDao();

    public static DatabaseRoom getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseRoom.class, "trip_app_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
