package com.iti.mad41.tripia.ui.activity.tripservice;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.services.AlarmTripService;

public class TripAlarm extends AppCompatActivity {
    Button startTrip;
    ImageView alrmIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_alarm);
        startTrip = findViewById(R.id.stratTripBtn);
        alrmIcon = findViewById(R.id.alarmIcon);
        startTrip.setOnClickListener(v -> {
            Intent intentService = new Intent(getApplicationContext(), AlarmTripService.class);
            getApplicationContext().stopService(intentService);
            finish();
        });

    }

    private void animateClock() {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(alrmIcon, "rotation", 0f, 20f, 0f, -20f, 0f);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimation.setDuration(800);
        rotateAnimation.start();
    }

}