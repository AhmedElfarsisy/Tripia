package com.iti.mad41.tripia.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.helper.Constants;
import com.iti.mad41.tripia.model.Note;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.FirebaseRepo;
import com.iti.mad41.tripia.ui.activity.main.MainActivity;
import com.iti.mad41.tripia.ui.activity.tripservice.TripAlarmActivity;

import java.util.ArrayList;


public class FloatingAppIconService extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;
    private TextView notesTextView;
    NotificationManager notificationManager;
    private static final String CHANNEL_ID = "100";

    public FloatingAppIconService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Inflate the floating view layout we created
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.floating_icon_layout, null);

        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);

        //The root element of the collapsed view layout
        final View collapsedView = mFloatingView.findViewById(R.id.collapse_view);
        //The root element of the expanded view layout
        final View expandedView = mFloatingView.findViewById(R.id.expanded_container);
        notesTextView = mFloatingView.findViewById(R.id.tripNots);
//        Set the close button
        ImageView
                closeButtonCollapsed = (ImageView) mFloatingView.findViewById(R.id.closeFloatingLayout);
        closeButtonCollapsed.setOnClickListener(v -> {
            stopSelf();
        });


        //Set the close button
        ImageView closeButton = (ImageView) mFloatingView.findViewById(R.id.closeCollapse);
        closeButton.setOnClickListener(v -> {
            collapsedView.setVisibility(View.VISIBLE);
            expandedView.setVisibility(View.GONE);
        });


        //Open the application on thi button click
        ImageView openButton = mFloatingView.findViewById(R.id.open_button);
        openButton.setOnClickListener(v -> {   //Open the application  click.
            Intent intent = new Intent(FloatingAppIconService.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //close the service and remove view from the view hierarchy
            stopSelf();
        });

        //Drag and move floating view using user's touch action.
        mFloatingView.findViewById(R.id.root_container).setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);


                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                //When user clicks on the image view of the collapsed layout,
                                //visibility of the collapsed layout will be changed to "View.GONE"
                                //and expanded view will become visible.
                                collapsedView.setVisibility(View.GONE);
                                expandedView.setVisibility(View.VISIBLE);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);


                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FirebaseRepo firebaseRepo = new FirebaseRepo();
//        intent.getStringExtra(Constants.TRIP_Firebase_ID_KEY)
        firebaseRepo.getTripNotes("fbd8608e-6976-4165-85f0-c06c816159ca"
                , new FirebaseDelegate() {
                    @Override
                    public void onGetNotesSuccess(ArrayList<Note> notesList) {
                        for (Note note : notesList) {
                            notesTextView.append(note.getNoteBody() + "\n");
                        }
                    }
                });

        notesTextView.setText(intent.getStringExtra(Constants.TRIP_TITLE_KEY));

        showNotification(intent.getStringExtra(Constants.TRIP_TITLE_KEY));
        return START_NOT_STICKY;
    }

    /**
     * Detect if the floating view is collapsed or expanded.
     *
     * @return true if the floating view is collapsed.
     */
    private boolean isViewCollapsed() {
        return mFloatingView == null || mFloatingView.findViewById(R.id.collapse_view).getVisibility() == View.VISIBLE;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }

    private void showNotification(String tripTitle) {
        Intent notificationIntent = new Intent(this, TripAlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            String channelName = "Tripia";
            int imporatnce = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("its time to go with Tripia");
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle(tripTitle)
                    .setContentText("Start your Trip now With Tripia")
                    .setContentIntent(pendingIntent);
            startForeground(1, builder.build());

        }
    }
}