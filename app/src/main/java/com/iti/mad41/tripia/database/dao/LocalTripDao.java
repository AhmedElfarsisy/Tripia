package com.iti.mad41.tripia.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.iti.mad41.tripia.database.dto.LocalTrip;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface LocalTripDao {
    //CRUD Operations

    //create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(LocalTrip trip);

    //Read
    //Give it the state of the trips you want
    @Query("SELECT * FROM local_trip WHERE tripState=:runningState")
    Single<List<LocalTrip>> getUpComingTrips(String runningState);


    @Query("SELECT * FROM local_trip WHERE tripState=:done and tripState=:canceledState")
    Single<List<LocalTrip>> getHistoryTrips(String done, String canceledState);

    @Query("SELECT * FROM local_trip WHERE id=:tripId")
    Single<LocalTrip> getTripById(int tripId);

    //Update
    @Update
    Completable updateTrip(LocalTrip trip);

    //Delete
    @Query("DELETE FROM local_trip WHERE id=:tripId")
    Completable deleteTrip(int tripId);

}
