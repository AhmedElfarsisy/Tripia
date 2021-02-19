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
    private static final String TAG = "AuthViewModel";
    public MutableLiveData<Boolean> isSignedinSuccessed = new MutableLiveData<>();
    IFirebaseRepo firebaseRepo;

    public AuthViewModel(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
        firebaseRepo.setDelegate(this);
    }

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.w(TAG, "Email: " + account.getId());
            firebaseRepo.handleGoogleToken(account);
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onHandleGoogleTokenSuccess(FirebaseUser user) {
        isSignedinSuccessed.setValue(true);
    }

    @Override
    public void onHandleGoogleTokenFailure(Exception exception) {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
