package com.iti.mad41.tripia.database.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int notesId;
    @ColumnInfo(name = "note_body")
    public String noteBody;
}
