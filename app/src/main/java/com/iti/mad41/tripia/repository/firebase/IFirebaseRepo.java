package com.iti.mad41.tripia.repository.firebase;

public interface IFirebaseRepo {
    void loginWithFirebase(String email, String password);

    void registerWithFirebase(String email, String password);

    void setDelegate(FirebaseDelegate delegate);



}
