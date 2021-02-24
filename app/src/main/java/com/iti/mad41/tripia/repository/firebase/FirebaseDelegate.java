package com.iti.mad41.tripia.repository.firebase;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseDelegate {
    default void onSigninSuccess() {
    }

    default void onSigninFailure(String localizedMessage) {
    }

    default void onRegisterSuccess() {

    }

    default void onRegisterFailure(String localizedMessage) {
    }

    default void onHandleFacebookTokenSuccess(FirebaseUser user){

    }

    default void onHandleFacebookTokenFailure(Exception exception){

    }

    default void onHandleGoogleTokenSuccess(FirebaseUser user){

    }

    default void onHandleGoogleTokenFailure(Exception exception){

    }

    default void onHandleImageB64Success(String imageB64){

    }

    default void onHandleImageB64Error(Exception exception){

    }

}
