package com.iti.mad41.tripia.ui.activity.auth;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AuthFactory  {
    FragmentManager fragmentManager;
//implements ViewModelProvider.Factory
    public AuthFactory(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

//    @NonNull
//    @Override
//    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(AuthViewModel.class)) {
//            return (T) new AuthViewModel();
//        }
//        return null;
//    }
}
