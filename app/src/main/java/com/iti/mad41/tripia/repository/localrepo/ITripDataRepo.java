package com.iti.mad41.tripia.repository.localrepo;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ITripDataRepo<T> {

    Completable createTrip(T obj);

    Observable<List<T>> getUpComingTrip();

    Observable<List<T>> getHistoryTrips();

    Single<T> getTripById(int tripId);

    Completable updateTrip(T obj);

    Completable deleteTrip(int tripId);

    Observable<List<T>> getUploadedFailedTrips();

}
