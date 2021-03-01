package com.iti.mad41.tripia.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.ui.activity.tripservice.TripAlarmActivity;


public class AlarmTripService extends Service {

    MediaPlayer mediaPlayer;
    NotificationManager notificationManager;
    private static final String CHANNEL_ID = "100";


    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarmsound);
        mediaPlayer.setLooping(true);
//        Intent activityIntent = new Intent(this, TripAlarmActivity.class);
//        //setDataOnIntent(activityIntent, intent);
//        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(activityIntent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        showNotification(intent.getStringExtra(Constants.TRIP_TITLE_KEY));

        mediaPlayer.start();

        return START_STICKY;
    }

    private void showNotification(String tripTitle) {
        Intent notificationIntent = new Intent(this, TripAlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            String channelName = "Tripia";
            int imporatnce = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, imporatnce);
            channel.setDescription("its time to go with Tripia");
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setAutoCancel(false)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(tripTitle)
                    .setContentText("Start your Trip now With Tripia")
                    .setContentIntent(pendingIntent);
            startForeground(1, builder.build());

        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}