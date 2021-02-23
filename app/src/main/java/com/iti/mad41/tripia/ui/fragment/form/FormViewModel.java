package com.iti.mad41.tripia.ui.fragment.form;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.Pair;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.iti.mad41.tripia.helper.Validations;
import com.iti.mad41.tripia.model.Trip;
import com.iti.mad41.tripia.repository.firebase.FirebaseDelegate;
import com.iti.mad41.tripia.repository.firebase.IFirebaseRepo;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FormViewModel extends ViewModel implements FirebaseDelegate {

    Calendar calendar;
    Context context;
    private static final String TAG = "FormViewModel";
    Trip  trip = new Trip();
    String checkDayOfMonth = "";
    String checkSelectedHour = "";
    IFirebaseRepo firebaseRepo;
    public MutableLiveData<Boolean> isNavigateToNotes = new MutableLiveData<>();
    public MutableLiveData<Double> startLongitude = new MutableLiveData<>();
    public MutableLiveData<Double> startLatitude = new MutableLiveData<>();
    public MutableLiveData<String> startAddress = new MutableLiveData<>();
    public MutableLiveData<Double> destinationLongitude = new MutableLiveData<>();
    public MutableLiveData<Double> destinationLatitude = new MutableLiveData<>();
    public MutableLiveData<String> destinationAddress = new MutableLiveData<>();
    public MutableLiveData<String> addressImageB64 = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNavigateFromStartAddress = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNavigateFromDestinationAddress = new MutableLiveData<>();
    public MutableLiveData<Pair<Boolean,Trip>> mutableLiveDataDate = new MutableLiveData<>();
    int yearV,monthV,dayV,hourV,minutesV;

    Calendar selectedDate = Calendar.getInstance();

    public MutableLiveData<String> startDate = new MutableLiveData<>();
    public MutableLiveData<String> startTime = new MutableLiveData<>();

    public MutableLiveData<Long> timeStamp = new MutableLiveData<>();
    public FormViewModel(IFirebaseRepo firebaseRepo) {
        isNavigateToNotes.setValue(false);
        this.firebaseRepo = firebaseRepo;
        firebaseRepo.setDelegate(this);
    }
    public void setContext(Context context) {
        this.context = context;
    }
    public void navigateToNotes() {
        isNavigateToNotes.setValue(true);
    }

    public void navigateFromStartAddress() {
        isNavigateFromStartAddress.setValue(true);
    }

    public void navigateFromDestinationAddress() {
        isNavigateFromDestinationAddress.setValue(true);
    }

    public void setStartAddressData(String address, Double latitude, Double longitude){
        startAddress.setValue(address);
        startLatitude.setValue(latitude);
        startLongitude.setValue(longitude);
    }

    public void setDestinationAddressData(String address, Double latitude, Double longitude){
        destinationAddress.setValue(address);
        destinationLatitude.setValue(latitude);
        destinationLongitude.setValue(longitude);
    }
    public void onDisplayTimerDialogClick() {
         calendar = Calendar.getInstance();
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Log.i(TAG, "onTimeSet: " + selectedHour + ":" + selectedMinute);
                checkSelectedHour = ""+selectedHour;
                hourV=selectedHour;
                minutesV=selectedMinute;
                selectedDate.set(yearV, monthV, dayV, hourV, minutesV);
                startTime.setValue( String.valueOf(selectedHour) + ":" + String.valueOf(selectedMinute));
                if (!Validations.isEmpty(checkDayOfMonth)) {
                    calendar.set(yearV, monthV, dayV, hourV, minutesV);
                    Log.i(TAG, "onTimeSet:if valid and set  Calender and time stamp is  "+calendar.getTimeInMillis()+" "+yearV+ " "+monthV+ " "+dayV+ " "+hourV + " "+minutesV);
                    timeStamp.setValue(calendar.getTimeInMillis());
                }
            }
        }, selectedDate.get(Calendar.HOUR), selectedDate.get(Calendar.MINUTE), false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDisplayDateDialogClick() {
        calendar =Calendar.getInstance();
        DatePickerDialog dd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.i(TAG, "onDateSet: " + year + " " + month + " " + dayOfMonth);
                startDate.setValue(dayOfMonth + " / " + month + " / " + year);
                dayV=dayOfMonth;
                monthV=month;
                yearV=year;
                calendar.set(Calendar.YEAR,yearV);
                calendar.set(Calendar.MONTH,monthV);
                calendar.set(Calendar.DAY_OF_MONTH,dayV);
                checkDayOfMonth = ""+month;
                selectedDate.set(yearV, monthV, dayV);
                if (!Validations.isEmpty(checkSelectedHour)) {
                    calendar.set(yearV, monthV, dayV, hourV, minutesV);
                    String r =parseTimeStamp(calendar.getTimeInMillis());
                    Log.i(TAG, "onDateSet:if valid and set Calender and time stamp is  "+r+" " +calendar.getTimeInMillis()+" "+yearV+ " "+monthV+ " "+dayV+ " "+hourV + " "+minutesV);
                    timeStamp.setValue(calendar.getTimeInMillis());
                }
            }
        },selectedDate.get(Calendar.YEAR),selectedDate.get(Calendar.MONTH),selectedDate.get(Calendar.DATE));
        dd.show();

    }
    private String parseTimeStamp(long postDate) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(postDate);
        String date = DateFormat.format("dd-MM-yyy ,hh:mm", calendar).toString();
        return date;
    }

    public void fetchPhoto(List<PhotoMetadata> metadata){
        firebaseRepo.fetchPhoto(metadata);
    }

    @Override
    public void onHandleImageB64Success(String imageB64) {
        addressImageB64.setValue(imageB64);
    }
}