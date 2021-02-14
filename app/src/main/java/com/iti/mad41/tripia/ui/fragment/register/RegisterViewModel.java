package com.iti.mad41.tripia.ui.fragment.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    MutableLiveData<String> userName = new MutableLiveData<>();
    MutableLiveData<String> email = new MutableLiveData<>();
    MutableLiveData<String> password = new MutableLiveData<>();


    public RegisterViewModel() {
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}