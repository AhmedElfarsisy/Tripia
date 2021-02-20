package com.iti.mad41.tripia.repository.localrepo;

import android.app.Application;
import android.content.Context;

import com.iti.mad41.tripia.database.DatabaseRoom;
import com.iti.mad41.tripia.database.dto.TripHistory;
import com.iti.mad41.tripia.database.dto.UpComingTrip;

public class TripsDataRepository implements ITripDataRepo {
    private static TripsDataRepository INSTANCE;
    static Application application;
    private DatabaseRoom databaseRoom;

    private TripsDataRepository(Application application) {
        this.application = application;
        DatabaseRoom.getInstance(application);
    }

    public static TripsDataRepository getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (TripsDataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TripsDataRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    //UpComing Trips DATABASE METHODS
    ///////////////////////////////////////////////////////////////////////////////////
    public void insertUpComingTrip(UpComingTrip trip) {
        databaseRoom.upComingTripDao().insert(trip);
    }

    public void getAllUpcomingTripsWithNotes() {
        databaseRoom.upComingTripDao().getAllTripsWithNotes();
    }

    public void deleteUpcomingTrip(int tripId) {
        databaseRoom.upComingTripDao().deleteTrip(tripId);
    }

    public void deleteAllUpcomingTrip() {
        databaseRoom.upComingTripDao().deleteAll();
    }
/////////////////////////////////////////////////////////////////////////

    //History of Trips  DEAL with DB METHODS
    public void insertTripHistory(TripHistory trip) {
        databaseRoom.tripHistoryDao().insert(trip);
    }

    public void getAllTripsHistory() {
        databaseRoom.tripHistoryDao().getAllTripsHistory();
    }

    public void deleteTripFromHistory(int tripId) {
        databaseRoom.tripHistoryDao().deleteTripFromHistory(tripId);
    }

    public void deleteAllHistoryTrip() {
        databaseRoom.tripHistoryDao().deleteAll();
    }
/////////////////////////////////////////////////////////////////////////
//Notes of Trips  DEAL with DB METHODS


}
