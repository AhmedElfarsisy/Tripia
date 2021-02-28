package com.iti.mad41.tripia.model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.iti.mad41.tripia.broadcast.TripBroadcastReceiver;
import com.iti.mad41.tripia.helper.Constants;

import java.util.List;
import java.util.Random;

public class Trip implements Parcelable {
    private String id;
    private String tripTitle;
    private String startAddress;
    private String destinationAddress;

    private Long dateTime;
    private String imageUrl;
    private Double startLongitude;
    private Double startLatitude;

    private Double destinationLongitude;
    private Double destinationLatitude;

    private int integerId;


    public Trip() {
    }

    public Trip(String id, String tripTitle, String startAddress, Double startLongitude, Double startLatitude, String destinationAddress, Double destinationLongitude, Double destinationLatitude, Long dateTime, String imageUrl) {
        this.id = id;
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationAddress = destinationAddress;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        integerId = new Random().nextInt(Integer.MAX_VALUE);
    }

    public Trip(String id, String tripTitle, String startAddress, String destinationAddress, Long dateTime, String imageUrl) {
        this.id = id;
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
    }


    protected Trip(Parcel in) {
        id = in.readString();
        tripTitle = in.readString();
        startAddress = in.readString();
        destinationAddress = in.readString();
        if (in.readByte() == 0) {
            dateTime = null;
        } else {
            dateTime = in.readLong();
        }
        imageUrl = in.readString();
        startLongitude = in.readDouble();
        startLatitude = in.readDouble();
        destinationLongitude = in.readDouble();
        destinationLatitude = in.readDouble();
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
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

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(tripTitle);
        dest.writeString(startAddress);
        dest.writeString(destinationAddress);
        if (dateTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(dateTime);
        }
        dest.writeString(imageUrl);
        dest.writeDouble(startLongitude);
        dest.writeDouble(startLatitude);
        dest.writeDouble(destinationLongitude);
        dest.writeDouble(destinationLatitude);
    }


    public void schedule(Context context) {
        Log.i("myTrip", "onscheduleFrist: onClick ");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, TripBroadcastReceiver.class);
        loadDataToIntent(intent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, integerId, intent, 0);

        Log.i("myTrip", "====: ==== after BroadCast  ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i("myTrip", "====:before ====  setExact  ");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent);
            Log.i("myTrip", "====: ==== after setExact  ");

        }
    }

    private void loadDataToIntent(Intent intent) {
        intent.putExtra(Constants.TRIP_Firebase_ID_KEY, id);
        intent.putExtra(Constants.TRIP_TITLE_KEY, tripTitle);
        intent.putExtra(Constants.TRIP_START_LAT_KEY, startLatitude);
        intent.putExtra(Constants.TRIP_START_Log_KEY, startLongitude);
        intent.putExtra(Constants.TRIP_DESTINATION_Lat_KEY, destinationLatitude);
        intent.putExtra(Constants.TRIP_DESTINATION_Log_KEY, destinationLongitude);
        intent.putExtra(Constants.TRIP_DATE_KEY, dateTime);
        intent.putExtra(Constants.TRIP_START_ADDRESS_KEY, startAddress);
        intent.putExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY, destinationAddress);
    }

    public void cancelAlarm(Context context, int alarmID) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, TripBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmID, intent, 0);
        alarmManager.cancel(alarmPendingIntent);

    }
}
