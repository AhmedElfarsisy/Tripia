package com.iti.mad41.tripia.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.iti.mad41.tripia.database.dto.Notes;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Notes notes);

    @Query("DELETE FROM trip_notes WHERE note_id = :noteId")
    void deleteNote(int noteId);



}
