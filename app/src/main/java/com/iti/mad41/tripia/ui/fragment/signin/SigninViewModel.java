package com.iti.mad41.tripia.ui.fragment.signin;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseUser;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.facebook.FacebookDelegate;
import com.iti.mad41.tripia.repository.facebook.IFacebookRepo;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleDelegate;
import com.iti.mad41.tripia.repository.google.GoogleRepo;
import com.iti.mad41.tripia.repository.google.IGoogleRepo;

public class SigninViewModel extends ViewModel implements FirebaseDelegate, FacebookDelegate, GoogleDelegate {
    FirebaseDelegate delegate;
    public MutableLiveData<Boolean> doSignin = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignedinSuccessed = new MutableLiveData<>();

    public MutableLiveData<Boolean> isPasswordValid = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEmailValid = new MutableLiveData<>();
    public MutableLiveData<String> isSignedinFailure = new MutableLiveData<>();

    //    public MutableLiveData<Pair<String, Boolean>> isSignedinFailure = new MutableLiveData<>();
    public MutableLiveData<Boolean> navigateToSignup = new MutableLiveData<>();

    IFirebaseRepo firebaseRepo;
    IFacebookRepo facebookRepo;
    IGoogleRepo googleRepo;

    public SigninViewModel(IFirebaseRepo firebaseRepo, IFacebookRepo facebookRepo, IGoogleRepo googleRepo) {
        this.firebaseRepo = firebaseRepo;
        this.facebookRepo = facebookRepo;
        this.googleRepo = googleRepo;
        firebaseRepo.setDelegate(this);
        facebookRepo.setDelegate(this);
        googleRepo.setDelegate(this);
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

    public void handleUseFacebookRegistration(){
        facebookRepo.handleUseFacebookSignin();
    }

    public void handleUseGoogleRegistration() { googleRepo.handleUseGoogleSigin(); }


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

    @Override
    public void onFacebookRegistrationSuccess(LoginResult loginResult) {
        firebaseRepo.handleFacebookToken(loginResult.getAccessToken());
    }

    @Override
    public void onFacebookRegistrationCancel() {

    }

    @Override
    public void onFacebookRegistrationError(FacebookException error) {

    }

    @Override
    public void onHandleFacebookTokenSuccess(FirebaseUser user) {
        isSignedinSuccessed.setValue(true);
    }

    @Override
    public void onHandleGoogleTokenSuccess(FirebaseUser user) {
        isSignedinSuccessed.setValue(true);
        Log.i("EMAIL_FROM_FACEBOOK", user.getEmail());
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