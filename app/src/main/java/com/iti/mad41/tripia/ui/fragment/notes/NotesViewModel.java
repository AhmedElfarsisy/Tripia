package com.iti.mad41.tripia.ui.fragment.notes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

public class NotesViewModel extends ViewModel {
    MutableLiveData<Boolean> addTitle = new MutableLiveData<>();
    MutableLiveData<Boolean> isClickSkip = new MutableLiveData<>();
    MutableLiveData<Boolean> isClickDone = new MutableLiveData<>();
    IFirebaseRepo firebaseRepo;


    public NotesViewModel(IFirebaseRepo firebaseRepo) {
        addTitle.setValue(false);
        isClickSkip.setValue(false);
        this.firebaseRepo = firebaseRepo;
    }

    public void clickOnAddTitle() {
        addTitle.setValue(true);

    }

    public void clickSkip() {
        addTitle.setValue(true);

    }

    public void clickDone() {
        isClickDone.setValue(true);
    }


    public void setTrip(Trip trip) {
        firebaseRepo.writeTrip(trip);
    }


}