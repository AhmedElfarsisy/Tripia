package com.iti.mad41.tripia.database.dto;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "upcoming_trip")
public class UpComingTrip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "upcoming_id")
    private int upComingTripId;
    private String tripTitle;
    private String startLongitude;
    private String startLatitude;

    private String destinationLongitude;
    private String destinationLatitude;

    private String startAddress;
    private String destinationAddress;

    private String tripDate;

    private boolean isRepeatable;

    private boolean isRoundTrip;
    private boolean isUploadedtofirebase;


    public int getUpComingTripId() {
        return upComingTripId;
    }

    public void setUpComingTripId(int upComingTripId) {
        this.upComingTripId = upComingTripId;
    }


    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public void setRepeatable(boolean repeatable) {
        isRepeatable = repeatable;
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }

    public boolean isUploadedtofirebase() {
        return isUploadedtofirebase;
    }

    public void setUploadedtofirebase(boolean uploadedtofirebase) {
        isUploadedtofirebase = uploadedtofirebase;
    }
}
