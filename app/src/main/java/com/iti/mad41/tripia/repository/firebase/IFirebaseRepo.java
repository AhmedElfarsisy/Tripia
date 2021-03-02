package com.iti.mad41.tripia.repository.firebase;

import android.graphics.Bitmap;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.iti.mad41.tripia.database.dto.Note;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.model.User;

import java.util.List;

public interface IFirebaseRepo {
    void loginWithFirebase(String email, String password);

    void registerWithFirebase(String email, String password);

    void handleFacebookToken(AccessToken token);

    void handleGoogleToken(GoogleSignInAccount googleSignInAccount);

    void writeNewUser(User user);

    void setDelegate(FirebaseDelegate delegate);


    void signOut();

    void writeTrip(Trip trip);

    void writeNotes(String tripId, List<Note> note);

    void fetchPhoto(List<PhotoMetadata> metadata);

    void changeTripState(String state, String tripId);

    void subscribeToUpcomingTrips();

    void subscribeToPreviousTrips();

    void deleteTrip(String tripId);

}
