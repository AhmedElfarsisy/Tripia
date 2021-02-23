package com.iti.mad41.tripia.repository.firebase;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
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
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.model.User;

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
            if(task.isSuccessful()){
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
            if(task.isSuccessful()){
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
        Query queries=userNameRef.orderByChild("userName").equalTo(user.getUserName());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users").push().setValue(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        queries.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public void writeTrip(Trip trip) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(currentUser.getUid()).push().setValue(trip);
    }





}
