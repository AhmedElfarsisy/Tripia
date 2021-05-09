package com.iti.mad41.tripia.ui.fragment.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.facebook.IFacebookRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.IGoogleRepo;
import com.iti.mad41.tripia.ui.fragment.signin.SigninViewModel;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    IFirebaseRepo firebaseRepo;
    IFacebookRepo facebookRepo;
    IGoogleRepo googleRepo;

    public RegisterViewModelFactory(IFirebaseRepo firebaseRepo, IFacebookRepo facebookRepo, IGoogleRepo googleRepo) {
        this.firebaseRepo = firebaseRepo;
        this.facebookRepo = facebookRepo;
        this.googleRepo = googleRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(firebaseRepo, facebookRepo, googleRepo);
        }
        return null;
    }
}
