package com.iti.mad41.tripia.database.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int notesId;
    private int upComingTripId;
    @ColumnInfo(name = "note_body")
    private String noteBody;

    public Notes(int notesId, int upComingTripId, String noteBody) {
        this.notesId = notesId;
        this.upComingTripId = upComingTripId;
        this.noteBody = noteBody;
    }



    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    public int getUpComingTripId() {
        return upComingTripId;
    }

    public void setUpComingTripId(int upComingTripId) {
        this.upComingTripId = upComingTripId;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }
}
