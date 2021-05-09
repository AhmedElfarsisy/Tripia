package com.iti.mad41.tripia.ui.activity.auth;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleDelegate;

public class AuthViewModel extends ViewModel implements FirebaseDelegate {

    public AuthViewModel(IFirebaseRepo firebaseRepo) {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
