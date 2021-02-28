package com.iti.mad41.tripia.ui.fragment.more;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

public class MoreViewModel extends ViewModel {

    IFirebaseRepo firebaseRepo;
    MutableLiveData<Boolean> isNavigateToProfile = new MutableLiveData<>();
    MutableLiveData<Boolean> isSignedOut = new MutableLiveData<>();

    public MoreViewModel(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
        isNavigateToProfile.setValue(false);
    }

    public void navigateToProfile() {
        isNavigateToProfile.setValue(true);
    }

    public void navigationToProfileCompleted() {
        isNavigateToProfile.setValue(false);
    }

    public void signOut() {
        firebaseRepo.signOut();
        isSignedOut.setValue(true);
    }
}