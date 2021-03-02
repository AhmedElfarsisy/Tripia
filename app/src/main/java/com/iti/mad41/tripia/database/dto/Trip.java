package com.iti.mad41.tripia.database.dto;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.iti.mad41.tripia.broadcast.TripBroadcastReceiver;
import com.iti.mad41.tripia.helper.Constants;

import java.util.Random;

@Entity(tableName = "local_trip")
public class Trip implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tripTitle;
    private String startAddress;
    private String destinationAddress;
    private Long dateTime;
    private String imageUrl;
    private Double startLongitude;
    private Double startLatitude;
    private Double destinationLongitude;
    private Double destinationLatitude;
    private NotesListOwner notesListOwner;
    private boolean isUpload;
    private String repeate;
    private String tripState;
    @Ignore
    private String firebaseId;
    private String status;
    private int alarmId;

    @Ignore
    public Trip() {

    }

    public Trip(String tripTitle, String startAddress, Double startLongitude, Double startLatitude, String destinationAddress, Double destinationLongitude, Double destinationLatitude, Long dateTime, String repeate, boolean isUpload) {
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationAddress = destinationAddress;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.dateTime = dateTime;
        this.isUpload = isUpload();
        this.repeate = repeate;
        status = Constants.TRIP_RUNNING;
    }

    public Trip(String firebaseId, String tripTitle, String startAddress, Double startLongitude, Double startLatitude, String destinationAddress, Double destinationLongitude, Double destinationLatitude, Long dateTime, String imageUrl) {
        this.firebaseId = firebaseId;
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.startLongitude = startLongitude;
        this.startLatitude = startLatitude;
        this.destinationAddress = destinationAddress;
        this.destinationLongitude = destinationLongitude;
        this.destinationLatitude = destinationLatitude;
        this.dateTime = dateTime;
        this.imageUrl = imageUrl;
        status = Constants.TRIP_RUNNING;
        alarmId = new Random().nextInt(Integer.MAX_VALUE);
    }

    @Ignore
    public Trip(String tripTitle, String startAddress, String destinationAddress, Long dateTime) {
        this.tripTitle = tripTitle;
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.dateTime = dateTime;
    }


    protected Trip(Parcel in) {
        id = in.readInt();
        tripTitle = in.readString();
        startAddress = in.readString();
        destinationAddress = in.readString();
        if (in.readByte() == 0) {
            dateTime = null;
        } else {
            dateTime = in.readLong();
        }
        imageUrl = in.readString();
        if (in.readByte() == 0) {
            startLongitude = null;
        } else {
            startLongitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            startLatitude = null;
        } else {
            startLatitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            destinationLongitude = null;
        } else {
            destinationLongitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            destinationLatitude = null;
        } else {
            destinationLatitude = in.readDouble();
        }
        isUpload = in.readByte() != 0;
        repeate = in.readString();
        tripState = in.readString();
        firebaseId = in.readString();
        status = in.readString();
        alarmId = in.readInt();
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


    public NotesListOwner getNotesListOwner() {
        return notesListOwner;
    }

    public void setNotesListOwner(NotesListOwner notesListOwner) {
        this.notesListOwner = notesListOwner;
    }

    public String getRepeate() {
        return repeate;
    }

    public void setRepeate(String repeate) {
        this.repeate = repeate;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripState() {
        return tripState;
    }

    public void setTripState(String tripState) {
        this.tripState = tripState;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
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
        if (startLongitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(startLongitude);
        }
        if (startLatitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(startLatitude);
        }
        if (destinationLongitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(destinationLongitude);
        }
        if (destinationLatitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(destinationLatitude);
        }
        dest.writeByte((byte) (isUpload ? 1 : 0));
        dest.writeString(repeate);
        dest.writeString(tripState);
        dest.writeString(firebaseId);
        dest.writeString(status);
        dest.writeInt(alarmId);
    }


    public void schedule(Context context) {
        Log.i("myTrip", "onscheduleFrist: onClick ");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, TripBroadcastReceiver.class);
        intent.putExtra(Constants.TRIP_ID_KEY, firebaseId);
        intent.putExtra(Constants.TRIP_TITLE_KEY, tripTitle);
        intent.putExtra(Constants.TRIP_START_LAT_KEY, startLatitude);
        intent.putExtra(Constants.TRIP_START_Log_KEY, startLongitude);
        intent.putExtra(Constants.TRIP_DESTINATION_Lat_KEY, destinationLatitude);
        intent.putExtra(Constants.TRIP_DESTINATION_Log_KEY, destinationLongitude);
        intent.putExtra(Constants.TRIP_DATE_KEY, dateTime);
        intent.putExtra(Constants.TRIP_START_ADDRESS_KEY, startAddress);
        intent.putExtra(Constants.TRIP_DESTINATION_ADDRESS_KEY, destinationAddress);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);

        Log.i("myTrip", "====: ==== after BroadCast  ");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.i("myTrip", "====:before ====  setExact  ");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent);
            Log.i("myTrip", "====: ==== after setExact  ");

        }
    }

    public void cancelAlarm(Context context, int alarmID) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, TripBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmID, intent, 0);
        alarmManager.cancel(alarmPendingIntent);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id='" + id + '\'' +
                ", tripTitle='" + tripTitle + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", dateTime=" + dateTime +
                ", startLongitude=" + startLongitude +
                ", startLatitude=" + startLatitude +
                ", destinationLongitude=" + destinationLongitude +
                ", destinationLatitude=" + destinationLatitude +
                ", alarmId=" + alarmId +
                '}';
    }
}
