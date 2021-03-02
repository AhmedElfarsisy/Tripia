package com.iti.mad41.tripia.ui.activity.tripservice;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.services.AlarmTripService;
import com.iti.mad41.tripia.services.FloatingAppIconService;

public class TripAlarmActivity extends AppCompatActivity {
    Button startTrip;
    ImageView alrmIcon;
    String TripTitle;
    Double startLong;
    Double startLat;
    String startAddress;
    String destinationAddress;
    Double destinationLat;
    Double destinationLong;
    String tripNotesID;
    FirebaseRepo firebaseRepo;
    String tripId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_alarm);
        startTrip = findViewById(R.id.startTripBtn);
        alrmIcon = findViewById(R.id.alarmIcon);
        firebaseRepo = new FirebaseRepo();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.TRIP_TITLE_KEY)) {
            Log.i("myTrip", Constants.TRIP_TITLE_KEY + " ::::::onCreate: ");
            tripNotesID = intent.getStringExtra(Constants.TRIP_Firebase_ID_KEY);
            TripTitle = intent.getStringExtra(Constants.TRIP_TITLE_KEY);

            startLong = intent.getDoubleExtra(Constants.TRIP_START_Log_KEY, 0.0);

            startLat = intent.getDoubleExtra(Constants.TRIP_START_LAT_KEY, 0.0);

            destinationLong = intent.getDoubleExtra(Constants.TRIP_DESTINATION_Log_KEY, 0.0);

            destinationLat = intent.getDoubleExtra(Constants.TRIP_START_LAT_KEY, 0.0);
            startAddress = intent.getStringExtra(Constants.TRIP_START_ADDRESS_KEY);

            destinationAddress = intent.getStringExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY);
            tripId = intent.getStringExtra(Constants.TRIP_ID_KEY);
        }
        startTrip.setOnClickListener(v -> {


            Intent intentService = new Intent(getApplicationContext(), AlarmTripService.class);
            getApplicationContext().stopService(intentService);
            displayTrack(startAddress, destinationAddress);
            startService(new Intent(TripAlarmActivity.this, FloatingAppIconService.class).putExtra(Constants.TRIP_ID_KEY, tripNotesID));
            firebaseRepo.changeTripState(Constants.TRIP_FINISHED, tripId);
            finish();
        });
        animateClock();
    }

    private void animateClock() {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(alrmIcon, "rotation", 0f, 20f, 0f, -20f, 0f);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimation.setDuration(800);
        rotateAnimation.start();
    }

    private void displayTrack(String Start, String destination) {
        Uri uri = Uri.parse("http://maps.google.com/maps?daddr=" + destination);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onCancelTrip(View view) {
        Intent intentService = new Intent(getApplicationContext(), AlarmTripService.class);
        getApplicationContext().stopService(intentService);
        finish();
    }
}