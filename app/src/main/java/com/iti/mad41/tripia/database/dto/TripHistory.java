package com.iti.mad41.tripia.database.dto;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "history_trip")
public class TripHistory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int historyTripId;

    public String tripTitle;

    public String startLongitude;
    public String startLatitude;

    public String destinationLongitude;
    public String destinationLatitude;

    public String startAddress;
    public String destinationAddress;

    public Calendar tripDate;

    public String tripState;

    public boolean isUploadedToFirebase;


}
