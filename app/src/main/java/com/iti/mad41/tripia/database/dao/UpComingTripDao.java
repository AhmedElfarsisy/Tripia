package com.iti.mad41.tripia.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.iti.mad41.tripia.database.dto.Notes;
import com.iti.mad41.tripia.database.dto.TripHistory;
import com.iti.mad41.tripia.database.dto.TripWithNotes;
import com.iti.mad41.tripia.database.dto.UpComingTrip;

import java.util.List;

@Dao
public interface UpComingTripDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UpComingTrip trip);

    @Query("DELETE FROM upcoming_trip")
    void deleteAll();

    @Query("DELETE FROM upcoming_trip WHERE upcoming_id = :noteId")
    void deleteTrip(int noteId);

    @Transaction
    @Query("SELECT * FROM upcoming_trip")
    public List<TripWithNotes> getAllTripsWithNotes();

//    @Transaction
//    @Query("SELECT * FROM upcoming_trip WHERE upcoming_id =:upcomingId")
//    public List<Notes> getNotes(int upcomingId);


}
