package com.iti.mad41.tripia.ui.fragment.signin;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

public class SiginViewModelFactory implements ViewModelProvider.Factory {


    IFirebaseRepo firebaseRepo;

    public SiginViewModelFactory(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SigninViewModel.class)) {
            return (T) new SigninViewModel(firebaseRepo);
        }
        return null;
    }
}
