package com.iti.mad41.tripia.repository.firebase;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface IFirebaseRepo {
    void loginWithFirebase(String email, String password);

    void registerWithFirebase(String email, String password);

    void handleFacebookToken(AccessToken token);

    void handleGoogleToken(GoogleSignInAccount googleSignInAccount);

    void setDelegate(FirebaseDelegate delegate);

}
