package com.iti.mad41.tripia.repository.localrepo;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.iti.mad41.tripia.database.DatabaseRoom;
import com.iti.mad41.tripia.database.dto.TripHistory;
import com.iti.mad41.tripia.database.dto.TripWithNotes;
import com.iti.mad41.tripia.database.dto.UpComingTrip;

import java.util.ArrayList;
import java.util.List;

public class TripsDataRepository implements ITripDataRepo {
    private static TripsDataRepository INSTANCE;
    static Context context;
    private DatabaseRoom databaseRoom;

    public void insertDummyData() {

        List<TripHistory> list = new ArrayList<>();
        List<UpComingTrip> upcomminglist = new ArrayList<>();

        upcomminglist.add(new UpComingTrip(0, "Trip1", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", false, false, false));
        upcomminglist.add(new UpComingTrip(1, "myTrip2", "32.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", false, true, false));
        upcomminglist.add(new UpComingTrip(2, "upcomingTitle2", "33.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", false, false, false));
        upcomminglist.add(new UpComingTrip(3, "upcomingTitle3", "34.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", true, true, true));
        upcomminglist.add(new UpComingTrip(4, "upcomingTitle4", "35.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", true, true, false));


//        list.add(new TripHistory(0, "HistTitle1", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", "Canceld", false));
//        list.add(new TripHistory(1, "HistTite2", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", "Canceld", false));
//        list.add(new TripHistory(2, "HistTitle3", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", "Canceld", false));
//        list.add(new TripHistory(3, "HistTitle4", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", "Canceld", false));
//        list.add(new TripHistory(4, "HistTitle5", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", "Canceld", false));


        for (UpComingTrip trip : upcomminglist) {
            insertUpComingTrip(trip);
            Log.i("myDatabase", "insertDummyData: ====================   loooooop" + "id:   " + trip.getUpComingTripId() + " title :  " + trip.getTripTitle());
        }
    }

    public void getUpComingTripDummy() {
        List<TripWithNotes> allUpcomingTripsWithNotes = getAllUpcomingTripsWithNotes();
        for (TripWithNotes trip : allUpcomingTripsWithNotes) {
            getAllUpcomingTripsWithNotes();
            Log.i("myDatabase", "getDummyData: ====================   loooooop" + "id:   " +
                    trip.getUpComingTrip().getUpComingTripId() + " title :  " + trip.getUpComingTrip().getTripTitle());
        }
    }

    private TripsDataRepository(Context context) {
        this.context = context;
        databaseRoom = DatabaseRoom.getInstance(context);
    }

    public static TripsDataRepository getINSTANCE(Context application) {
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

    public List<TripWithNotes> getAllUpcomingTripsWithNotes() {
        List<TripWithNotes> allTripsWithNotes = databaseRoom.upComingTripDao().getAllTripsWithNotes();
        return allTripsWithNotes;
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

    public List<TripHistory> getAllTripsHistory() {
        List<TripHistory> allTripsHistory = databaseRoom.tripHistoryDao().getAllTripsHistory();
        return allTripsHistory;
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
