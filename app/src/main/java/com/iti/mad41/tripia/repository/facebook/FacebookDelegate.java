package com.iti.mad41.tripia.repository.facebook;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

public interface FacebookDelegate {
    default void onFacebookRegistrationSuccess(LoginResult loginResult){

    };

    default void onFacebookRegistrationCancel(){

    };

    default void onFacebookRegistrationError(FacebookException error){

    };
}
