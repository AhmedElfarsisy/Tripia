package com.iti.mad41.tripia.database.dto;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_trip")
public class LocalTrip {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tripTitle;
    private String startAddress;
    private String destinationAddress;
    private Long dateTime;
    private String imageUrl;
    private Double startLongitude;
    private Double startLatitude;
    private Double destinationLongitude;
    private Double destinationLatitude;
    private NotesListOwner notesListOwner;
    private boolean isUpload;
    private String repeate;
    private String tripState;


    public LocalTrip(String tripTitle, String startAddress, Double startLongitude, Double startLatitude, String destinationAddress, Double destinationLongitude, Double destinationLatitude, Long dateTime, String repeate, boolean isUpload, String tripState) {
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationAddress = destinationAddress;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.dateTime = dateTime;
        this.isUpload = isUpload();
        this.tripState = tripState;
        this.repeate = repeate;
        this.isUpload = isUpload;
    }

    @Ignore
    public LocalTrip(String tripTitle, String startAddress, String destinationAddress, Long dateTime) {
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.dateTime = dateTime;
    }


    public NotesListOwner getNotesListOwner() {
        return notesListOwner;
    }

    public void setNotesListOwner(NotesListOwner notesListOwner) {
        this.notesListOwner = notesListOwner;
    }

    public String getRepeate() {
        return repeate;
    }

    public void setRepeate(String repeate) {
        this.repeate = repeate;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripState() {
        return tripState;
    }

    public void setTripState(String tripState) {
        this.tripState = tripState;
    }

}
