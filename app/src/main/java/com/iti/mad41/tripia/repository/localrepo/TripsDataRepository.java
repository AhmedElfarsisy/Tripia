package com.iti.mad41.tripia.repository.localrepo;

import android.content.Context;


import com.iti.mad41.tripia.database.DatabaseRoom;
import com.iti.mad41.tripia.database.dto.LocalTrip;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class TripsDataRepository implements ITripDataRepo {
    private static TripsDataRepository INSTANCE;
    static Context context;
    private DatabaseRoom databaseRoom;

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


    @Override
    public Completable createTrip(Object obj) {
        Completable insert = databaseRoom.tripDao().insert((LocalTrip) obj);
        return insert;
    }


    //GET Trips on running state
    @Override
    public Single<List<LocalTrip>> getUpComingTrip(String tripState) {
        Single<List<LocalTrip>> upComingTrips = databaseRoom.tripDao().getUpComingTrips(tripState);
        return upComingTrips;
    }

    @Override
    public Single<List<LocalTrip>> getHistoryTrips(String done, String canceledState) {
        Single<List<LocalTrip>> historyTrips = databaseRoom.tripDao().getHistoryTrips(done, canceledState);
        return historyTrips;
    }

    @Override
    public Single<LocalTrip> getTripById(int tripId) {
        Single<LocalTrip> tripById = databaseRoom.tripDao().getTripById(tripId);
        return tripById;
    }

    @Override
    public Completable updateTrip(Object obj) {
        Completable updateTrip = databaseRoom.tripDao().updateTrip((LocalTrip) obj);
        return updateTrip;
    }

    @Override
    public Completable deleteTrip(int tripId) {
        Completable deleteTrip = databaseRoom.tripDao().deleteTrip(tripId);
        return deleteTrip;
    }



}
