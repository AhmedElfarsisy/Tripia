package com.iti.mad41.tripia.repository.facebook;

public interface IFacebookRepo {
    void handleUseFacebookSignin();

    void setDelegate(FacebookDelegate delegate);
}
