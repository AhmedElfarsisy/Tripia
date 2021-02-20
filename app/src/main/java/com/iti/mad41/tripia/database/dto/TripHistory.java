package com.iti.mad41.tripia.database.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_trip")
public class TripHistory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "history_id")
    private int historyTripId;

    private String tripTitle;

    private String startLongitude;
    private String startLatitude;

    private String destinationLongitude;
    private String destinationLatitude;

    private String startAddress;
    private String destinationAddress;

    private String tripDate;

    private String tripState;

    private boolean isUploadedToFirebase;

    public TripHistory(String tripTitle, String startLongitude, String startLatitude, String destinationLongitude, String destinationLatitude, String startAddress, String destinationAddress, String tripDate, String tripState, boolean isUploadedToFirebase) {
        this.tripTitle = tripTitle;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.tripDate = tripDate;
        this.tripState = tripState;
        this.isUploadedToFirebase = isUploadedToFirebase;
    }

    public int getHistoryTripId() {
        return historyTripId;
    }

    public void setHistoryTripId(int historyTripId) {
        this.historyTripId = historyTripId;
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

    public String getTripState() {
        return tripState;
    }

    public void setTripState(String tripState) {
        this.tripState = tripState;
    }

    public boolean isUploadedToFirebase() {
        return isUploadedToFirebase;
    }

    public void setUploadedToFirebase(boolean uploadedToFirebase) {
        isUploadedToFirebase = uploadedToFirebase;
    }
}
