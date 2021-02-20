package com.iti.mad41.tripia.repository.firebase;

public interface FirebaseDelegate {
    default void onSigninSuccess() {
    }

    default void onSigninFailure(String localizedMessage) {
    }

    default void onRegisterSuccess() {

    }

    default void onRegisterFailure(String localizedMessage) {
    }


}
