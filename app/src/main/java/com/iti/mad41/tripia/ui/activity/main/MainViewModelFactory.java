package com.iti.mad41.tripia.ui.activity.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.ui.fragment.main.upcomingTrips.UpcomingTripsViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    ITripDataRepo tripDataRepo;

    public MainViewModelFactory(ITripDataRepo tripDataRepo) {
        this.tripDataRepo = tripDataRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(tripDataRepo);
        }
        return null;
    }
}
