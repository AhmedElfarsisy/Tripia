package com.iti.mad41.tripia.ui.fragment.main.previousTrips;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.facebook.IFacebookRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.IGoogleRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.fragment.main.previousTrips.PreviousTripsViewModel;
import com.iti.mad41.tripia.ui.fragment.register.RegisterViewModel;

public class PreviousTripsViewModelFactory implements ViewModelProvider.Factory {
    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;

    public PreviousTripsViewModelFactory(IFirebaseRepo firebaseRepo, TripsDataRepository tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PreviousTripsViewModel.class)) {
            return (T) new PreviousTripsViewModel(firebaseRepo, tripsDataRepository);
        }
        return null;
    }
}
