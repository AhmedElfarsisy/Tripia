package com.iti.mad41.tripia.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iti.mad41.tripia.database.dto.TripHistory;

import java.util.List;

@Dao
public interface TripHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TripHistory tr);

    @Query("DELETE FROM history_trip")
    void deleteAll();

    @Query("DELETE FROM history_trip WHERE history_id = :tripId")
    void deleteTripFromHistory(int tripId);

    @Query("SELECT * FROM history_trip ORDER BY history_id DESC")
    List<TripHistory> getAllTripsHistory();
}
