package com.iti.mad41.tripia.ui.fragment.signin;

import android.util.Log;
import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

public class SigninViewModel extends ViewModel implements FirebaseDelegate {
    FirebaseDelegate delegate;
    public MutableLiveData<Boolean> doSignin = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignedinSuccessed = new MutableLiveData<>();

    public MutableLiveData<Boolean> isPasswordValid = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEmailValid = new MutableLiveData<>();
    public MutableLiveData<String> isSignedinFailure = new MutableLiveData<>();

    //    public MutableLiveData<Pair<String, Boolean>> isSignedinFailure = new MutableLiveData<>();
    public MutableLiveData<Boolean> navigateToSignup = new MutableLiveData<>();

    IFirebaseRepo firebaseRepo;

    public SigninViewModel(IFirebaseRepo firebaseRepo) {
        this.firebaseRepo = firebaseRepo;
        firebaseRepo.setDelegate(this);
        initBooleanValues();
    }


    public void signinUser(String email, String password) {
        if (isValidEmail(email)) {
            if (isValidPassword(password)) {
                firebaseRepo.loginWithFirebase(email, password);
            } else {

            }
        } else {
        }

    }


    public void signinpressed() {
        doSignin.setValue(true);
    }


    public void goNavigateText() {
        navigateToSignup.setValue(true);
    }


    public void setNavigateToSignupComplete() {
        navigateToSignup.setValue(false);
    }

    @Override
    public void onSigninSuccess() {
        isSignedinSuccessed.setValue(true);
    }

    @Override
    public void onSigninFailure(String localizedMessage) {
        Log.i("myapp", "===onSigninFailure:=== "+localizedMessage);
        isSignedinFailure.setValue(localizedMessage);
    }


    private boolean isValidPassword(String password) {
        boolean matches = password.matches(Constants.VALIDATION_REGEX_PASS);
        isPasswordValid.setValue(matches);
        return matches;
    }

    private boolean isValidEmail(String email) {
        boolean matches = email.matches(Constants.VALIDATION_REGEX_EMIAL);
        isEmailValid.setValue(matches);
        return matches;
    }

    private void initBooleanValues() {
        doSignin.setValue(false);
        isEmailValid.setValue(false);
        isPasswordValid.setValue(false);
        isSignedinSuccessed.setValue(false);
        isSignedinFailure.setValue("");
        navigateToSignup.setValue(false);
    }
}