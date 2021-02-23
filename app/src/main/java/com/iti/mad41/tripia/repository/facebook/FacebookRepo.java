package com.iti.mad41.tripia.repository.facebook;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;

import java.util.Arrays;

public class FacebookRepo implements IFacebookRepo {
    private Fragment fragment;
    private FacebookDelegate delegate;
    private CallbackManager callbackManager;

    public FacebookRepo(Fragment fragment, CallbackManager callbackManager){
        this.fragment = fragment;
        this.callbackManager = callbackManager;
    }

    public void setDelegate(FacebookDelegate delegate) {
        this.delegate = delegate;
    }

    public void handleUseFacebookSignin(){
        LoginManager.getInstance().logInWithReadPermissions(
                fragment,
                Arrays.asList("email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                delegate.onFacebookRegistrationSuccess(loginResult);
            }

            @Override
            public void onCancel() {
                delegate.onFacebookRegistrationCancel();
            }

            @Override
            public void onError(FacebookException error) {
                delegate.onFacebookRegistrationError(error);
            }
        });
    }
}
