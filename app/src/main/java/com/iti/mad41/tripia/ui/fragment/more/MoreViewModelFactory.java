package com.iti.mad41.tripia.ui.fragment.more;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.facebook.IFacebookRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.IGoogleRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.fragment.signin.SigninViewModel;

public class MoreViewModelFactory implements ViewModelProvider.Factory {


    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;


    public MoreViewModelFactory(IFirebaseRepo firebaseRepo, TripsDataRepository tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoreViewModel.class)) {
            return (T) new MoreViewModel(firebaseRepo, tripsDataRepository);
        }
        return null;
    }
}
