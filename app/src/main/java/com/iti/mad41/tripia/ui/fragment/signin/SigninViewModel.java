package com.iti.mad41.tripia.ui.fragment.signin;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.iti.mad41.tripia.helper.Validations;
import com.iti.mad41.tripia.model.User;
import com.iti.mad41.tripia.repository.facebook.FacebookDelegate;
import com.iti.mad41.tripia.repository.facebook.IFacebookRepo;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleDelegate;
import com.iti.mad41.tripia.repository.google.IGoogleRepo;

public class SigninViewModel extends ViewModel implements FirebaseDelegate, FacebookDelegate, GoogleDelegate {
    public MutableLiveData<Boolean> doSignin = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignedinSuccessed = new MutableLiveData<>();
    public MutableLiveData<String> isSignedinFailure = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSignedinCanceled = new MutableLiveData<>();
    public MutableLiveData<User> isSocialSuccessed = new MutableLiveData<>();
    public MutableLiveData<String> isSocialFailure = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSocialCanceled = new MutableLiveData<>();

    public MutableLiveData<Boolean> isPasswordNotValid = new MutableLiveData<>();
    public MutableLiveData<Boolean> isEmailNotValid = new MutableLiveData<>();

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
        if (Validations.isValidEmail(email)) {
            if (Validations.isValidPassword(password)) {
                firebaseRepo.loginWithFirebase(email, password);
            } else {
                isPasswordNotValid.setValue(true);
            }
        } else {
            isEmailNotValid.setValue(true);
        }

    }

    public void writeNewUser(User user){
        firebaseRepo.writeNewUser(user);
    }

    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            firebaseRepo.handleGoogleToken(account);
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            isSocialFailure.setValue(e.getMessage());
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
        isSignedinCanceled.setValue(true);
    }

    @Override
    public void onFacebookRegistrationError(FacebookException error) {
        isSignedinFailure.setValue(error.getMessage());
    }

    @Override
    public void onHandleFacebookTokenSuccess(FirebaseUser user) {
        isSocialSuccessed.setValue(new User(user.getDisplayName(), user.getEmail(), ""));
    }

    @Override
    public void onHandleFacebookTokenFailure(Exception exception) {
        isSignedinFailure.setValue(exception.getMessage());
    }

    @Override
    public void onHandleGoogleTokenSuccess(FirebaseUser user) {
        isSocialSuccessed.setValue(new User(user.getDisplayName(), user.getEmail(), ""));
    }

    @Override
    public void onHandleGoogleTokenFailure(Exception exception) {
        isSignedinFailure.setValue(exception.getMessage());
    }

    private void initBooleanValues() {
        doSignin.setValue(false);
        isSignedinSuccessed.setValue(false);
        isSignedinFailure.setValue("");
        isSignedinCanceled.setValue(false);
        isEmailNotValid.setValue(false);
        isPasswordNotValid.setValue(false);
        navigateToSignup.setValue(false);
    }



}