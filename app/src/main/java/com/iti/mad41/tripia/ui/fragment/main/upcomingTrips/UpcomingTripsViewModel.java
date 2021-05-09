package com.iti.mad41.tripia.ui.fragment.main.upcomingTrips;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class UpcomingTripsViewModel extends ViewModel implements FirebaseDelegate {
    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<Trip>> liveUpcomingTripsList = new MutableLiveData<>();

    public UpcomingTripsViewModel(IFirebaseRepo firebaseRepo, ITripDataRepo tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
        firebaseRepo.checkForTrips();
        firebaseRepo.subscribeToUpcomingTrips();
        firebaseRepo.setDelegate(this);
    }

    public void deleteTrip(Trip trip){
        firebaseRepo.deleteTrip(trip.getFirebaseId());
    }

    @Override
    public void onSubscribeToTripsSuccess(List<Trip> tripsList) {
        liveUpcomingTripsList.setValue(tripsList);
        for(Trip trip : tripsList){
            tripsDataRepository
                    .createTrip(trip)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {}, throwable -> {});
        }
    }

    @Override
    public void onCheckForTripsFailure() {
        Log.i("CHECK_FOR_TRIPS", "onCheckForTripsFailure Enterance");
        tripsDataRepository
                .getUpComingTrip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Trip>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Trip> trips) {
                        liveUpcomingTripsList.setValue(trips);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
