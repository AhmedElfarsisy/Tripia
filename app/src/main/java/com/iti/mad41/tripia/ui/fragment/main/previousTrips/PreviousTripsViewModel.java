package com.iti.mad41.tripia.ui.fragment.main.previousTrips;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

import java.util.ArrayList;
import java.util.List;

public class PreviousTripsViewModel extends ViewModel implements FirebaseDelegate {
    IFirebaseRepo firebaseRepo;
    public MutableLiveData<List<Trip>> liveUpcomingTripsList = new MutableLiveData<>();

    public PreviousTripsViewModel(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
        firebaseRepo.subscribeToPreviousTrips();
        firebaseRepo.setDelegate(this);
    }

    public void deleteTrip(Trip trip){
        firebaseRepo.deleteTrip(trip.getFirebaseId());
    }

    @Override
    public void onSubscribeToTripsSuccess(List<Trip> tripsList) {
        liveUpcomingTripsList.setValue(tripsList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
