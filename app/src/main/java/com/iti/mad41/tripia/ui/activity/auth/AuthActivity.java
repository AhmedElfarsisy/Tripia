package com.iti.mad41.tripia.ui.activity.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.database.DatabaseRoom;
import com.iti.mad41.tripia.database.dto.TripHistory;
import com.iti.mad41.tripia.databinding.ActivityAuthBinding;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.repository.google.GoogleRepo;
import com.iti.mad41.tripia.repository.localrepo.TripsDataRepository;
import com.iti.mad41.tripia.ui.activity.main.MainActivity;
import com.iti.mad41.tripia.ui.fragment.notes.NotesFragment;
import com.iti.mad41.tripia.ui.fragment.signin.SiginViewModelFactory;
import com.iti.mad41.tripia.ui.fragment.signin.SigninFragment;
import com.iti.mad41.tripia.ui.fragment.signin.SigninViewModel;

import java.util.Calendar;

public class AuthActivity extends AppCompatActivity {
    private static final String TAG = "AuthActivity";
    ActivityAuthBinding binding;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SigninFragment signinFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);


        //        TripsDataRepository repository = TripsDataRepository.getINSTANCE(getApplicationContext());
//        repository.insertDummyData();
//        repository.getUpComingTripDummy();
////        Log.i("myDatabase", "onCreate:  === go insert " );
////        TripHistory tripHistory = new TripHistory(0, "HistTitle1", "31.00000", "131.1245", "15.01111", "124.12345", "ElmoezStreet", "the kingdom of no where", "12/3/2021", "Canceld", false);
////        DatabaseRoom.getInstance(this).tripHistoryDao().insert(tripHistory);
////        Log.i("myDatabase", "onCreate:  === insertedddddddddddddddddddddddd " );

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            signinFragment = new SigninFragment();
            NotesFragment notesFragment = new NotesFragment();
            fragmentTransaction = fragmentManager.beginTransaction()
                    .add(R.id.auth_fragment_container_view, notesFragment, Constants.SIGNIN_FRAGMENT);
            fragmentTransaction.commit();
        } else {
            signinFragment = (SigninFragment) fragmentManager.findFragmentByTag(Constants.SIGNIN_FRAGMENT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentManager.findFragmentByTag(Constants.SIGNIN_FRAGMENT).onActivityResult(requestCode, resultCode, data);
        if (fragmentManager.findFragmentByTag(Constants.SIGNUP_FRAGMENT) != null)
            fragmentManager.findFragmentByTag(Constants.SIGNUP_FRAGMENT).onActivityResult(requestCode, resultCode, data);
    }
}