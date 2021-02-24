package com.iti.mad41.tripia.ui.fragment.main.upcomingTrips;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.facebook.IFacebookRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.IGoogleRepo;
import com.iti.mad41.tripia.ui.fragment.register.RegisterViewModel;

public class UpcomingTripsViewModelFactory implements ViewModelProvider.Factory {
    IFirebaseRepo firebaseRepo;

    public UpcomingTripsViewModelFactory(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UpcomingTripsViewModel.class)) {
            return (T) new UpcomingTripsViewModel(firebaseRepo);
        }
        return null;
    }
}
