package com.iti.mad41.tripia.repository.firebase;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.model.Note;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.model.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FirebaseRepo implements IFirebaseRepo {
    private FirebaseDelegate delegate;
    private Activity activity;
    private DatabaseReference mDatabase;

    public FirebaseRepo(Activity activity) {
        this.activity = activity;
    }

    public FirebaseRepo() {
    }

    public void setDelegate(FirebaseDelegate delegate) {
        this.delegate = delegate;
    }


    @Override
    public void loginWithFirebase(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                            Log.i("myapp", "loginWithFirebase: success ");
                            delegate.onSigninSuccess();
                        }
                ).addOnFailureListener(e -> {
            Log.i("myapp", "loginWithFirebase: Failure");
            delegate.onSigninFailure(e.getLocalizedMessage());
        });
    }

    @Override
    public void registerWithFirebase(String email, String password) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                            delegate.onSigninSuccess();
                        }
                ).addOnFailureListener(e -> delegate.onSigninFailure(e.getLocalizedMessage()));
    }

    @Override
    public void handleFacebookToken(AccessToken token) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                delegate.onHandleFacebookTokenSuccess(user);
            } else {
                delegate.onHandleFacebookTokenFailure(task.getException());
            }
        });
    }

    @Override
    public void handleGoogleToken(GoogleSignInAccount googleSignInAccount) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                delegate.onHandleGoogleTokenSuccess(user);
            } else {
                delegate.onHandleGoogleTokenFailure(task.getException());
            }
        });
    }

    @Override
    public void writeNewUser(User user) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userNameRef = rootRef.child("users");
        Query queries = userNameRef.orderByChild("email").equalTo(user.getEmail());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").child(currentUser.getUid()).setValue(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        queries.addListenerForSingleValueEvent(eventListener);
    }



    @Override
    public void signOut() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
    }


    @Override
    public void writeTrip(Trip trip) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(currentUser.getUid()).child("trips").child(trip.getId()).setValue(trip);
    }

    @Override
    public void writeNotes(String tripId, List<Note> note) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("notes").child(tripId).setValue(note);
    }

    @Override
    public void changeTripState(String state, String tripId){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(currentUser.getUid()).child("trips").child(tripId).child("status").setValue(state);
    }

    @Override
    public void fetchPhoto(List<PhotoMetadata> metadata) {
        if (metadata == null || metadata.isEmpty()) {
            return;
        }
        final PhotoMetadata photoMetadata = metadata.get(0);

        final FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                .setMaxWidth(500)
                .setMaxHeight(500)
                .build();
        PlacesClient placesClient = Places.createClient(activity);
        placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
            Bitmap bitmap = fetchPhotoResponse.getBitmap();
            delegate.onHandleImageB64Success(encodeFromBitmapToImg64(bitmap));
        }).addOnFailureListener((exception) -> {
            delegate.onHandleImageB64Error(exception);
            if (exception instanceof ApiException) {
                final ApiException apiException = (ApiException) exception;
                Log.e("IMAGE_ERROR", "Place not found: " + exception.getMessage());
                final int statusCode = apiException.getStatusCode();
                // TODO: Handle error with given status code.
            }
        });

    }

    public String encodeFromBitmapToImg64(Bitmap bmp) {

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bao); // bmp is bitmap from user image file
        bmp.recycle();
        byte[] byteArray = bao.toByteArray();
        String imageB64 = Base64.encodeToString(byteArray, Base64.URL_SAFE);
        return imageB64;
        //  store & retrieve this string which is URL safe(can be used to store in FBDB) to firebase
        // Use either Realtime Database or Firestore
    }

    @Override
    public void subscribeToUpcomingTrips(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query tripsQuery = mDatabase.child("users").child(currentUser.getUid()).child("trips").orderByChild("status").equalTo(Constants.TRIP_RUNNING);
        tripsQuery.addValueEventListener(new ValueEventListener() {
            ArrayList<Trip> tripsList = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tripsList.clear();
                for (DataSnapshot tripSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    tripsList.add(tripSnapshot.getValue(Trip.class));
                    Log.i("subscribeToTrips", tripSnapshot.getValue(Trip.class).getTripTitle());
                }
                delegate.onSubscribeToTripsSuccess(tripsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                delegate.onSubscribeToTripsCancel();
                // Getting Post failed, log a message
                Log.w("OnCancelled", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void subscribeToPreviousTrips() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query tripsQuery = mDatabase.child("users").child(currentUser.getUid()).child("trips");
        tripsQuery.addValueEventListener(new ValueEventListener() {
            List<Trip> tripsList = new ArrayList<>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tripsList.clear();
                for (DataSnapshot tripSnapshot : dataSnapshot.getChildren()) {
                    Trip trip = tripSnapshot.getValue(Trip.class);
                    if(trip.getStatus().equals(Constants.TRIP_FINISHED) || trip.getStatus().equals(Constants.TRIP_CANCELLED))
                        tripsList.add(tripSnapshot.getValue(Trip.class));
                }
                delegate.onSubscribeToTripsSuccess(tripsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                delegate.onSubscribeToTripsCancel();
                // Getting Post failed, log a message
                Log.w("OnCancelled", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void deleteTrip(String tripId){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tripRef = mDatabase.child("users").child(currentUser.getUid()).child("trips").child(tripId);
        tripRef.removeValue();
    }

}
