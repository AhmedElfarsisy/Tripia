package com.iti.mad41.tripia.ui.fragment.form;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingMethod;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class FormViewModel extends ViewModel {


    Context context;
    private static final String TAG = "FormViewModel";
     private String title ="1234" ;
    public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
  //  public MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
    public FormViewModel() {
        mutableLiveData.setValue(false);
    }
    public  void setContext(Context context){

        this.context=context;
    }
    public  void  navigateToNotes () {
        mutableLiveData.setValue(true);
        Log.i(TAG, "navigateToNotes: "+title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( @NonNull String title) {
        this.title = title;
    }

    public void onDisplayTimerDialogClick()
    {



    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onDisplayDateDialogClick()
    {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        //Calendar calendar =  Calendar.getInstance();
        int year =calendar.get(Calendar.YEAR);
        int month =calendar.get(Calendar.MONTH);
        int day =calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.i(TAG, "onDateSet: "+year+" "+month+" "+dayOfMonth);

                calendar.set(year,month,dayOfMonth);

            }
        },day,month,year);
            dd.show();






                //dd.setMinDate(long minDate);
        //setMaxDate(long maxDate)
    }
    public void set(CharSequence charSequence)
    {

    }



}