package com.iti.mad41.tripia.ui.fragment.main.upcomingTrips;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;

public class UpcomingTripsViewModelFactory implements ViewModelProvider.Factory {
    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;

    public UpcomingTripsViewModelFactory(IFirebaseRepo firebaseRepo, TripsDataRepository tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UpcomingTripsViewModel.class)) {
            return (T) new UpcomingTripsViewModel(firebaseRepo, tripsDataRepository);
        }
        return null;
    }
}
