package com.iti.mad41.tripia.database.dto;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TripWithNotes {
    @Embedded
    public UpComingTrip upComingTrip;
    @Relation(
            parentColumn = "upComingTripId",
            entityColumn = "NotesId"
    )
    public List<Notes> notesList;
}
