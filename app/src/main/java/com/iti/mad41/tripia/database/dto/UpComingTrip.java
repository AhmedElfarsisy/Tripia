package com.iti.mad41.tripia.database.dto;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.iti.mad41.tripia.broadcast.TripBroadcastReceiver;
import com.iti.mad41.tripia.helper.Constants;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "upcoming_trip")
public class UpComingTrip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "upcoming_id")
    private int upComingTripId;
    private String tripTitle;
    private String startLongitude;
    private String startLatitude;

    private String destinationLongitude;
    private String destinationLatitude;

    private String startAddress;
    private String destinationAddress;

    private Long tripDate;

    private boolean isRepeatable;
    private boolean isRoundTrip;
    private boolean isUploadedtofirebase;

    public UpComingTrip(int upComingTripId, String tripTitle, String startLongitude, String startLatitude, String destinationLongitude, String destinationLatitude, String startAddress, String destinationAddress, Long tripDate, boolean isRepeatable, boolean isRoundTrip, boolean isUploadedtofirebase) {
        this.upComingTripId = upComingTripId;
        this.tripTitle = tripTitle;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.tripDate = tripDate;
        this.isRepeatable = isRepeatable;
        this.isRoundTrip = isRoundTrip;
        this.isUploadedtofirebase = isUploadedtofirebase;
    }

    public int getUpComingTripId() {
        return upComingTripId;
    }

    public void setUpComingTripId(int upComingTripId) {
        this.upComingTripId = upComingTripId;
    }


    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Long getTripDate() {
        return tripDate;
    }

    public void setTripDate(Long tripDate) {
        this.tripDate = tripDate;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public void setRepeatable(boolean repeatable) {
        isRepeatable = repeatable;
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }

    public boolean isUploadedtofirebase() {
        return isUploadedtofirebase;
    }

    public void setUploadedtofirebase(boolean uploadedtofirebase) {
        isUploadedtofirebase = uploadedtofirebase;
    }


    public void schedule(Context context) {
        Log.i("myTrip", "onscheduleFrist: onClick ");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, TripBroadcastReceiver.class);
        intent.putExtra(Constants.TRIP_TITLE_KEY, tripTitle);
        intent.putExtra(Constants.TRIP_START_LAT_KEY, startLatitude);
        intent.putExtra(Constants.TRIP_START_Log_KEY, startLongitude);
        intent.putExtra(Constants.TRIP_DESTINATION_Lat_KEY, destinationLatitude);
        intent.putExtra(Constants.TRIP_DESTINATION_Log_KEY, destinationLongitude);
        intent.putExtra(Constants.TRIP_DATE_KEY, tripDate);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, upComingTripId, intent, 0);

        Log.i("myTrip", "====: ==== after BroadCast  ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i("myTrip", "====:before ====  setExact  ");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, tripDate, pendingIntent);
            Log.i("myTrip", "====: ==== after setExact  ");

        }
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, TripBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, upComingTripId, intent, 0);
        alarmManager.cancel(alarmPendingIntent);

    }

}
