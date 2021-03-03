package com.iti.mad41.tripia.repository.localrepo;

import android.content.Context;


import com.iti.mad41.tripia.database.DatabaseRoom;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.helper.Constants;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
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
        Completable insert = databaseRoom.tripDao().insert((Trip) obj);
        return insert;
    }


    //GET Trips on running state
    @Override
    public Observable<List<Trip>> getUpComingTrip() {
        Observable<List<Trip>> upComingTrips = databaseRoom.tripDao().getUpComingTrips(Constants.TRIP_RUNNING);
        return upComingTrips;
    }

    @Override
    public Observable<List<Trip>> getHistoryTrips() {
        Observable<List<Trip>> historyTrips = databaseRoom.tripDao().getHistoryTrips(Constants.TRIP_FINISHED, Constants.TRIP_CANCELLED);
        return historyTrips;
    }

    @Override
    public Single<Trip> getTripById(int tripId) {
        Single<Trip> tripById = databaseRoom.tripDao().getTripById(tripId);
        return tripById;
    }

    @Override
    public Completable updateTrip(Object obj) {
        Completable updateTrip = databaseRoom.tripDao().updateTrip((Trip) obj);
        return updateTrip;
    }

    @Override
    public Completable deleteTrip(int tripId) {
        Completable deleteTrip = databaseRoom.tripDao().deleteTrip(tripId);
        return deleteTrip;
    }

    @Override
    public Observable<List<Trip>> getUploadedFailedTrips() {
        Observable<List<Trip>> uploadedFailedTrips = databaseRoom.tripDao().getUploadedFailedTrips(false);
        return uploadedFailedTrips;
    }


}
