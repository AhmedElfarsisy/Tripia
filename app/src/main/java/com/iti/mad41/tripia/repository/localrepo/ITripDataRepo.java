package com.iti.mad41.tripia.repository.localrepo;

import com.iti.mad41.tripia.database.dto.LocalTrip;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ITripDataRepo<T> {

    Completable createTrip(T obj);

    Single<List<T>> getUpComingTrip(String tripState);

    Single<List<T>> getHistoryTrips(String done, String canceledState);

    Single<T> getTripById(int tripId);

    Completable updateTrip(T obj);

    Completable deleteTrip(int tripId);


}
