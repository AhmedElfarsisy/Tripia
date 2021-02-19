package com.iti.mad41.tripia.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.iti.mad41.tripia.database.dto.TripWithNotes;

import java.util.List;

@Dao
public interface UpComingTripDao {
    @Transaction
    @Query("SELECT * FROM UPCOMING_TRIP")
    public List<TripWithNotes> getAllTripsWithNotes();

}
