package com.iti.mad41.tripia.ui.activity.auth;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

public class AuthFactory implements ViewModelProvider.Factory {
    IFirebaseRepo firebaseRepo;

    //implements ViewModelProvider.Factory
    public AuthFactory(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            return (T) new AuthViewModel(firebaseRepo);
        }
        return null;
    }
}
