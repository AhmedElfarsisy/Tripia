package com.iti.mad41.tripia.repository.firebase;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.model.User;

public interface IFirebaseRepo {
    void loginWithFirebase(String email, String password);

    void registerWithFirebase(String email, String password);

    void handleFacebookToken(AccessToken token);

    void handleGoogleToken(GoogleSignInAccount googleSignInAccount);

    void writeNewUser(User user);

    void setDelegate(FirebaseDelegate delegate);

    void writeTrip(Trip trip);

}
