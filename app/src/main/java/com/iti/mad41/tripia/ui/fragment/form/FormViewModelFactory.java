package com.iti.mad41.tripia.ui.fragment.form;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.fragment.notes.NotesViewModel;

public class FormViewModelFactory implements ViewModelProvider.Factory {


    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;

    public FormViewModelFactory(IFirebaseRepo firebaseRepo, TripsDataRepository tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FormViewModel.class)) {
            return (T) new FormViewModel(firebaseRepo, tripsDataRepository);
        }
        return null;
    }
}
