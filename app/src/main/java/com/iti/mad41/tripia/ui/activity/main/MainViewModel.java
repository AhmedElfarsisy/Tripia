package com.iti.mad41.tripia.ui.activity.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Boolean> isNavigateToForm = new MutableLiveData<>();
    public MainViewModel() {
    }
    public  void  navigateToForm () {
        isNavigateToForm.setValue(true);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }



}