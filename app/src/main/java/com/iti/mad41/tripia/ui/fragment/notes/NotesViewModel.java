package com.iti.mad41.tripia.ui.fragment.notes;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Note;
import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotesViewModel extends ViewModel {
    MutableLiveData<Boolean> addTitle = new MutableLiveData<>();
    MutableLiveData<Boolean> isClickSkip = new MutableLiveData<>();
    MutableLiveData<Boolean> isClickDone = new MutableLiveData<>();
    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripDataRepo;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<String> operationResult = new MutableLiveData<>();


    public NotesViewModel(IFirebaseRepo firebaseRepo, ITripDataRepo tripsDataRepository) {
        addTitle.setValue(false);
        isClickSkip.setValue(false);
        this.firebaseRepo = firebaseRepo;
        tripDataRepo = tripsDataRepository;
    }

    public void clickOnAddTitle() {
        addTitle.setValue(true);

    }

    public void clickSkip() {
        addTitle.setValue(true);

    }

    public void clickDone() {
        isClickDone.setValue(true);
    }


    public void setTrip(Trip trip) {
        firebaseRepo.writeTrip(trip);
    }

    public void createLocalTrip(Trip trip) {
        tripDataRepo.createTrip(trip).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }
            @Override
            public void onComplete() {
                operationResult.setValue("Trip added successfully");
            }
            @Override
            public void onError(@NonNull Throwable e) {
                operationResult.setValue(e.getLocalizedMessage());
            }
        });

    }

    public void setNote(String tripId, List<Note> note) {
        firebaseRepo.writeNotes(tripId, note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}