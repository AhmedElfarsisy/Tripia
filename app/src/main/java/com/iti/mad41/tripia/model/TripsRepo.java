package com.iti.mad41.tripia.model;

import java.util.ArrayList;
import java.util.List;

public class TripsRepo {
    private List<Trip> tripsList;

    public TripsRepo(){
        tripsList = new ArrayList<>();
        tripsList.add(new Trip(1, "Go for gym", "Madinat nasr", "Smart gym", new Long(2), "https://images.unsplash.com/photo-1563857256304-d943ffb54ca8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1234&q=80"));
        tripsList.add(new Trip(2, "Shopping", "Masr Elgedida", "Carefour", new Long(2), "https://images.unsplash.com/photo-1566097260834-3b689c8c2457?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1234&q=80"));
        tripsList.add(new Trip(3, "Work", "Masr Elgedida", "Smart village", new Long(2), "https://images.unsplash.com/photo-1564030925462-8408eb31ad25?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2250&q=80"));
    }

    public List<Trip> getTripsList(){return tripsList;}

    public void deleteMovie(Trip trip) {tripsList.remove(trip);}
}
