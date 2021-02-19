package com.iti.mad41.tripia.ui.fragment.form;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FormViewModel extends ViewModel {


    private static final String TAG = "FormViewModel";
     private String title ="1234" ;
    public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
    public FormViewModel() {
        mutableLiveData.setValue(false);
    }
    public  void  navigateToNotes () {
        mutableLiveData.setValue(true);
        Log.i(TAG, "navigateToNotes: "+title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( @NonNull String title) {
        this.title = title;
    }



}