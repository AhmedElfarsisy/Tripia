package com.iti.mad41.tripia.ui.activity.form;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.ui.activity.main.MainViewModel;

public class FormActivityViewModelFactory implements ViewModelProvider.Factory {
    ITripDataRepo tripDataRepo;

    public FormActivityViewModelFactory(ITripDataRepo tripDataRepo) {
        this.tripDataRepo = tripDataRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FormActivityViewModel.class)) {
            return (T) new FormActivityViewModel(tripDataRepo);
        }
        return null;
    }
}
