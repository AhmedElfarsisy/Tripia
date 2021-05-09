package com.iti.mad41.tripia.ui.fragment.main.previousTrips;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PreviousTripsViewModel extends ViewModel implements FirebaseDelegate {
    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<List<Trip>> livePreviousTripsList = new MutableLiveData<>();

    public PreviousTripsViewModel(IFirebaseRepo firebaseRepo, ITripDataRepo tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
        firebaseRepo.checkForTrips();
        firebaseRepo.subscribeToPreviousTrips();
        firebaseRepo.setDelegate(this);
    }

    public void deleteTrip(Trip trip){
        firebaseRepo.deleteTrip(trip.getFirebaseId());
    }

    @Override
    public void onSubscribeToTripsSuccess(List<Trip> tripsList) {
        livePreviousTripsList.setValue(tripsList);
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
        tripsDataRepository
                .getHistoryTrips()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Trip>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Trip> trips) {
                        livePreviousTripsList.setValue(trips);
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
