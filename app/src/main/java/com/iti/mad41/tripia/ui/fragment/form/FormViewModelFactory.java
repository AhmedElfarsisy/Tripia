package com.iti.mad41.tripia.ui.fragment.form;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.ui.fragment.notes.NotesViewModel;

public class FormViewModelFactory implements ViewModelProvider.Factory {


    IFirebaseRepo firebaseRepo;

    public FormViewModelFactory(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FormViewModel.class)) {
            return (T) new FormViewModel(firebaseRepo);
        }
        return null;
    }
}
