package com.iti.mad41.tripia.ui.fragment.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

public class ProfileViewModel extends ViewModel {
    IFirebaseRepo firebaseRepo;
    MutableLiveData<Boolean> isOpenGallery = new MutableLiveData<>();

    public ProfileViewModel(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
        isOpenGallery.setValue(false);
    }

    public void navigateToGallary() {
        isOpenGallery.setValue(true);
    }
}