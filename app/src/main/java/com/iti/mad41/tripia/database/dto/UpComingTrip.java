    package com.iti.mad41.tripia.database.dto;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "upcoming_trip")
public class UpComingTrip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int upComingTripId;

    @Embedded
    public Notes tripNotes;

    public String tripTitle;
    public String startLongitude;
    public String startLatitude;

    public String destinationLongitude;
    public String destinationLatitude;

    public String startAddress;
    public String destinationAddress;

    public Calendar tripDate;

    public boolean isRepeatable;

    public boolean isRoundTrip;
    public boolean isUploadedtofirebase;

}
