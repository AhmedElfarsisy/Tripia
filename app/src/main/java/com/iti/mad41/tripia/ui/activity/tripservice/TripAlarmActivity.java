package com.iti.mad41.tripia.ui.activity.tripservice;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
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

public class TripAlarmActivity extends AppCompatActivity {
    Button startBtn;
    Button snoozeBtn;
    Button cancelBtn;
    ImageView alrmIcon;
    String tripId;
    String TripTitle;
    Double startLong;
    Double startLat;
    String startAddress;
    String destinationAddress;
    Double destinationLat;
    Double destinationLong;
    FirebaseRepo firebaseRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        tripId = intent.getStringExtra(Constants.TRIP_ID_KEY);
//        startAddress = intent.getStringExtra(Constants.TRIP_START_ADDRESS_KEY);
//        destinationAddress = intent.getStringExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY);
//        firebaseRepo = new FirebaseRepo();
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder
//                .setTitle(intent.getStringExtra(Constants.TRIP_TITLE_KEY))
//                .setMessage("Your trip is ready, Lets Start!")
//                .setCancelable(false)
//                .setPositiveButton("Start", new DialogInterface.OnClickListener()
//                {
//                    public void onClick(DialogInterface dialog, int id)
//                    {
//                        stopService();
//                        displayTrack(startAddress, destinationAddress);
//                        firebaseRepo.changeTripState(Constants.TRIP_FINISHED, tripId);
//                        dialog.cancel();
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
//                {
//                    public void onClick(DialogInterface dialog, int id)
//                    {
//                        stopService();
//                        firebaseRepo.changeTripState(Constants.TRIP_CANCELLED, tripId);
//                        dialog.cancel();
//                    }
//                })
//                .setNeutralButton("Snooze", new DialogInterface.OnClickListener()
//                {
//                    public void onClick(DialogInterface dialog, int id)
//                    {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
        setContentView(R.layout.activity_trip_alarm);
        alrmIcon = findViewById(R.id.alarmIcon);
        startBtn = findViewById(R.id.startTripBtn);
        snoozeBtn = findViewById(R.id.snoozeTripBtn);
        cancelBtn = findViewById(R.id.cancelTripBtn);
        firebaseRepo = new FirebaseRepo();
        Intent intent = getIntent();
        if (intent != null) {
            if(intent.hasExtra(Constants.TRIP_TITLE_KEY)){
                tripId = intent.getStringExtra(Constants.TRIP_ID_KEY);
                TripTitle = intent.getStringExtra(Constants.TRIP_TITLE_KEY);
                startLong = intent.getDoubleExtra(Constants.TRIP_START_Log_KEY, 0.0);
                startLat = intent.getDoubleExtra(Constants.TRIP_START_LAT_KEY, 0.0);
                destinationLong = intent.getDoubleExtra(Constants.TRIP_DESTINATION_Log_KEY, 0.0);
                destinationLat = intent.getDoubleExtra(Constants.TRIP_START_LAT_KEY, 0.0);
                startAddress = intent.getStringExtra(Constants.TRIP_START_ADDRESS_KEY);
                destinationAddress = intent.getStringExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY);

                Log.i("Alarm_Manager", "onCreate: " + "startAddress: " + startAddress);
                Log.i("Alarm_Manager", "onCreate: " + "destinationAddress" + destinationAddress);
                Log.i("Alarm_Manager", "onCreate: " + "startLat" + startLat);
                Log.i("Alarm_Manager", "onCreate: " + "destinationLat" + destinationLat);
                Log.i("Alarm_Manager", "onCreate: " + "destinationLong" +  destinationLong);
                Log.i("Alarm_Manager", "onCreate: " + "startLong" +  startLong);
            }
            Log.i("myTrip", Constants.TRIP_TITLE_KEY + " ::::::onCreate: " + TripTitle);
        }
        animateClock();
        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stopService();
                displayTrack(startAddress, destinationAddress);
                firebaseRepo.changeTripState(Constants.TRIP_FINISHED, tripId);
                finish();
            }
        });

        snoozeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stopService();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                stopService();
                firebaseRepo.changeTripState(Constants.TRIP_CANCELLED, tripId);
                finish();
            }
        });
    }

    private void stopService(){
        Intent intentService = new Intent(getApplicationContext(), AlarmTripService.class);
        getApplicationContext().stopService(intentService);
    }

    private void animateClock() {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(alrmIcon, "rotation", 0f, 20f, 0f, -20f, 0f);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimation.setDuration(800);
        rotateAnimation.start();
    }

    private void displayTrack(String Start, String destination) {
        Log.i("Alarm_Manager", "onCreate---: " + "startAddress: " + startAddress);
        Log.i("Alarm_Manager", "onCreate---: " + "destinationAddress" + destinationAddress);
        Log.i("Alarm_Manager", "onCreate---: " + "startLat" + startLat);
        Log.i("Alarm_Manager", "onCreate---: " + "destinationLat" + destinationLat);
        Log.i("Alarm_Manager", "onCreate---: " + "destinationLong" +  destinationLong);
        Log.i("Alarm_Manager", "onCreate---: " + "startLong" +  startLong);
        //Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + Start + "&destination=" + destination + "&travelmode=car");
        Uri uri = Uri.parse("http://maps.google.com/maps?daddr=" + destination);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}