package com.iti.mad41.tripia.broadcast;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.services.AlarmTripService;
import com.iti.mad41.tripia.ui.activity.tripservice.TripAlarmActivity;

public class TripBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("myTrip", Constants.TRIP_TITLE_KEY + " ::::::onReceive ALARM: " + intent.getStringExtra(Constants.TRIP_TITLE_KEY));
        Log.i("myTrip", Constants.TRIP_START_LAT_KEY + " ::::::onReceive ALARM: " + intent.getDoubleExtra(Constants.TRIP_START_LAT_KEY, 0.0));
        Log.i("myTrip", Constants.TRIP_START_Log_KEY + " ::::::onReceive ALARM: " + intent.getDoubleExtra(Constants.TRIP_START_Log_KEY, 0.0));
        Log.i("myTrip", Constants.TRIP_START_ADDRESS_KEY + " ::::::onReceive ALARM: " + intent.getStringExtra(Constants.TRIP_START_ADDRESS_KEY));
        Log.i("myTrip", Constants.TRIP_DESTINATION_ADDRESS_KEY + " ::::::onReceive ALARM: " + intent.getStringExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY));

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            startRescheduleTripAlarmsService(context);
        } else {
            Log.i("myTrip", "createTrip: onRecciver ");
            startTripAlarmService(context, intent);
        }
    }

    private void startRescheduleTripAlarmsService(Context context) {

    }


    private void startTripAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, AlarmTripService.class);
        setDataOnIntent(intentService, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }

        Intent activityIntent = new Intent(context, TripAlarmActivity.class);
        setDataOnIntent(activityIntent, intent);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }

    public void setDataOnIntent(Intent intentService, Intent intent) {
        Log.i("myTrip", Constants.TRIP_TITLE_KEY + " ::::::setDataOnIntent ALARM: " + intent.getStringExtra(Constants.TRIP_TITLE_KEY));
        intentService.putExtra(Constants.TRIP_ID_KEY, intent.getStringExtra(Constants.TRIP_ID_KEY));
        intentService.putExtra(Constants.TRIP_TITLE_KEY, intent.getStringExtra(Constants.TRIP_TITLE_KEY));
        intentService.putExtra(Constants.TRIP_START_LAT_KEY, intent.getDoubleExtra(Constants.TRIP_START_LAT_KEY, 0.0));
        intentService.putExtra(Constants.TRIP_START_Log_KEY, intent.getDoubleExtra(Constants.TRIP_START_Log_KEY, 0.0));
        intentService.putExtra(Constants.TRIP_DESTINATION_Lat_KEY, intent.getDoubleExtra(Constants.TRIP_DESTINATION_Lat_KEY, 0.0));
        intentService.putExtra(Constants.TRIP_DESTINATION_Log_KEY, intent.getDoubleExtra(Constants.TRIP_DESTINATION_Log_KEY, 0.0));
        intentService.putExtra(Constants.TRIP_DATE_KEY, intent.getLongExtra(Constants.TRIP_DATE_KEY, 0));
        intentService.putExtra(Constants.TRIP_START_ADDRESS_KEY, intent.getStringExtra(Constants.TRIP_START_ADDRESS_KEY));
        intentService.putExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY, intent.getStringExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY));
    }

}