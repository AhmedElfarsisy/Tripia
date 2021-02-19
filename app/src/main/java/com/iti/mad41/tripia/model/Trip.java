package com.iti.mad41.tripia.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Trip {
    private int id;
    private String title;
    private String startLocation;
    private String endLocation;
    private String dateTime;
    private String imageUrl;

    public Trip(int id, String title, String startLocation, String endLocation, String dateTime, String imageUrl) {
        this.id = id;
        this.title = title;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.dateTime = imageUrl;
    }
}
