package com.iti.mad41.tripia.repository.google;

public interface IGoogleRepo {
    void handleUseGoogleSigin();

    void setDelegate(GoogleDelegate delegate);
}
