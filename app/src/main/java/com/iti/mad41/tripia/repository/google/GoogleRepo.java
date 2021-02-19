package com.iti.mad41.tripia.repository.google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.iti.mad41.tripia.R;

public class GoogleRepo implements IGoogleRepo {
    public static final int RC_SIGN_IN = 1;
    private static final String WEB_CLIENT_ID = "444795866056-gc5pfr0bt6uej2b2kmgo8l2dh1im1gmm.apps.googleusercontent.com";
    private Activity activity;


    public GoogleRepo(Activity activity){
        this.activity = activity;
    }

    @Override
    public void setDelegate(GoogleDelegate delegate) {

    }

    @Override
    public void handleUseGoogleSigin() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(WEB_CLIENT_ID)
                .requestEmail()
                .requestProfile()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(activity, googleSignInOptions);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
