package com.iti.mad41.tripia.database.dto;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TripWithNotes {
    @Embedded
    private UpComingTrip upComingTrip;
    @Relation(
            parentColumn = "upcoming_id",
            entityColumn = "note_id"
    )
    private List<Notes> notesList;

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }

    public UpComingTrip getUpComingTrip() {
        return upComingTrip;
    }

    public void setUpComingTrip(UpComingTrip upComingTrip) {
        this.upComingTrip = upComingTrip;
    }
}
