package com.iti.mad41.tripia.repository.facebook;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

public interface FacebookDelegate {
    void onFacebookRegistrationSuccess(LoginResult loginResult);

    void onFacebookRegistrationCancel();

    void onFacebookRegistrationError(FacebookException error);
}
