package com.iti.mad41.tripia.ui.fragment.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.ui.fragment.more.MoreViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {


    IFirebaseRepo firebaseRepo;


    public ProfileViewModelFactory(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(firebaseRepo);
        }
        return null;
    }
}
