package com.iti.mad41.tripia.adapters;

import com.iti.mad41.tripia.model.Trip;

public interface onUpcomingTripsClickCallback {
    void onStartClick(Trip trip);
    void onDeleteClick(Trip trip);
}
