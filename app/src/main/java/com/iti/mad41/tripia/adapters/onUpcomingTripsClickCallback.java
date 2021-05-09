package com.iti.mad41.tripia.adapters;

import com.iti.mad41.tripia.database.dto.Trip;

public interface onUpcomingTripsClickCallback {
    void onStartClick(Trip trip);
    void onUpdateClick(Trip trip);
    void onDeleteClick(Trip trip);
}
