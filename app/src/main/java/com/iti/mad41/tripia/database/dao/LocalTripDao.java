package com.iti.mad41.tripia.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.iti.mad41.tripia.database.dto.Trip;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface LocalTripDao {
    //CRUD Operations

    //create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Trip trip);

    //Read
    //Give it the state of the trips you want
    @Query("SELECT * FROM local_trip WHERE status=:runningState")
    Observable<List<Trip>> getUpComingTrips(String runningState);


    @Query("SELECT * FROM local_trip WHERE status=:done OR status=:canceledState")
    Observable<List<Trip>> getHistoryTrips(String done, String canceledState);

    @Query("SELECT * FROM local_trip WHERE id=:tripId")
    Single<Trip> getTripById(int tripId);

    //Update
    @Update
    Completable updateTrip(Trip trip);

    //Delete
    @Query("DELETE FROM local_trip WHERE id=:tripId")
    Completable deleteTrip(int tripId);

    @Query("SELECT * FROM local_trip WHERE isUpload=:isUploaded")
    Observable<List<Trip>> getUploadedFailedTrips(boolean isUploaded);

    @Query("DELETE FROM local_trip ")
    Completable deleteAllTrips();

}
