package com.iti.mad41.tripia.ui.activity.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    MutableLiveData<Boolean> isNavigateToForm = new MutableLiveData<>();

    public MainViewModel(ITripDataRepo tripDataRepo) {
    }

    public  void  navigateToForm () {
        isNavigateToForm.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }



}