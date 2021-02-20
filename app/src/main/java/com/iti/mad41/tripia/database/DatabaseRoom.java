package com.iti.mad41.tripia.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.iti.mad41.tripia.database.dao.NoteDao;
import com.iti.mad41.tripia.database.dao.TripHistoryDao;
import com.iti.mad41.tripia.database.dao.UpComingTripDao;
import com.iti.mad41.tripia.database.dto.TripHistory;
import com.iti.mad41.tripia.database.dto.Notes;
import com.iti.mad41.tripia.database.dto.UpComingTrip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UpComingTrip.class, TripHistory.class, Notes.class}, version = 1, exportSchema = false)
public abstract class DatabaseRoom extends RoomDatabase {
    private static volatile DatabaseRoom INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract NoteDao NoteDao();

    public abstract TripHistoryDao tripHistoryDao();

    public abstract UpComingTripDao upComingTripDao();

    public static DatabaseRoom getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseRoom.class, "trip_app_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
