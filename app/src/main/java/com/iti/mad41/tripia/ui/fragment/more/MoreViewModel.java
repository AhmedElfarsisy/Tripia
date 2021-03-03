package com.iti.mad41.tripia.ui.fragment.more;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iti.mad41.tripia.database.dto.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;
import com.iti.mad41.tripia.repository.localrepo.ITripDataRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoreViewModel extends ViewModel implements FirebaseDelegate {

    IFirebaseRepo firebaseRepo;
    ITripDataRepo tripsDataRepository;
    MutableLiveData<Boolean> isNavigateToProfile = new MutableLiveData<>();
    MutableLiveData<Boolean> isSignedOut = new MutableLiveData<>();
    MutableLiveData<String> operationResult = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MoreViewModel(IFirebaseRepo firebaseRepo, ITripDataRepo tripsDataRepository) {
        this.firebaseRepo = firebaseRepo;
        this.tripsDataRepository = tripsDataRepository;
        firebaseRepo.setDelegate(this);
        isNavigateToProfile.setValue(false);
    }

    public void navigateToProfile() {
        isNavigateToProfile.setValue(true);
    }

    public void navigationToProfileCompleted() {
        isNavigateToProfile.setValue(false);
    }

    public void syncWithFirebase() {
        tripsDataRepository
                .getUploadedFailedTrips()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Trip>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull List<Trip> trips) {
                        for(Trip trip : trips){
                            trip.setFirebaseId(UUID.randomUUID().toString());
                            trip.setUpload(true);
                            firebaseRepo.writeTrip(trip);
                            firebaseRepo.writeNotes(trip.getFirebaseId(), trip.getNotesListOwner().getNotesList());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        operationResult.setValue(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        operationResult.setValue("Sync finished successfully");
                    }
                });
    }

    public void signOut() {
        firebaseRepo.signOut();
        isSignedOut.setValue(true);
    }

    @Override
    public void onWriteTripSuccess(Trip trip) {
        tripsDataRepository.updateTrip(trip);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}