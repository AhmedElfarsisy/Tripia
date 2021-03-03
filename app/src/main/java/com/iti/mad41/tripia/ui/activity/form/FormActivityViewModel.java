package com.iti.mad41.tripia.ui.activity.form;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FormActivityViewModel extends ViewModel {
    private ITripDataRepo tripsDataRepository;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<Trip> trip = new MutableLiveData<>();

    public FormActivityViewModel(ITripDataRepo tripsDataRepository) {
        this.tripsDataRepository = tripsDataRepository;
    }

    public void getTrip(int tripId) {
        tripsDataRepository
                .getTripById(tripId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Object o) {
                        trip.setValue((Trip)o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}
