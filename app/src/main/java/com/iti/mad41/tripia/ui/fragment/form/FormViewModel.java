package com.iti.mad41.tripia.ui.fragment.form;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingMethod;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class FormViewModel extends ViewModel {


    Context context;
    private static final String TAG = "FormViewModel";
    private String title = "1234";
    public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Double> startLongitude = new MutableLiveData<>();
    public MutableLiveData<Double> startLatitude = new MutableLiveData<>();
    public MutableLiveData<String> startAddress = new MutableLiveData<>();
    public MutableLiveData<Double> destinationLongitude = new MutableLiveData<>();
    public MutableLiveData<Double> destinationLatitude = new MutableLiveData<>();
    public MutableLiveData<String> destinationAddress = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNavigateFromStartAddress = new MutableLiveData<>();
    public MutableLiveData<Boolean> isNavigateFromDestinationAddress = new MutableLiveData<>();



    //  public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
    public FormViewModel() {
        mutableLiveData.setValue(false);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void navigateToNotes() {
        mutableLiveData.setValue(true);
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

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                //eReminderTime.setText( selectedHour + ":" + selectedMinute);
                Log.i(TAG, "onTimeSet: " + selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDisplayDateDialogClick() {
        Calendar calendar =Calendar.getInstance();
                //new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        //calendar.set(2000, 5, 5);
        //calendar.getTimeInMillis()
        //Calendar calendar =  Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH );
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.i(TAG, "onDateSet: " + year + " " + month + " " + dayOfMonth);
                calendar.set(year, month, dayOfMonth);
                //mutableLiveDataDate.setValue();

            }
        },year,month,day);
        dd.show();
        //dd.setMinDate(long minDate);
        //setMaxDate(long maxDate)
    }

    public void set(CharSequence charSequence) {

    }

    private String parseTimeStamp(long postDate) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(postDate);
        String date = DateFormat.format("dd-MM-yyy ,hh:mm", calendar).toString();
        return date;
    }

}