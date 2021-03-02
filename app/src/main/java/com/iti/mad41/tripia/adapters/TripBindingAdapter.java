package com.iti.mad41.tripia.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateFormat;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.iti.mad41.tripia.R;
import com.iti.mad41.tripia.helper.Constants;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TripBindingAdapter {
    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imageView, String imageUrl){
        Glide.with(imageView).load(imageUrl).into(imageView);
    }

    @BindingAdapter("android:getDateTime")
    public static void parseTimeStamp(TextView textView, long postDate) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(postDate);
        String date = DateFormat.format("dd-MM-yyy, hh:mm", calendar).toString();
        textView.setText(date);
    }

    @BindingAdapter("android:loadImageBitmap")
    public static void parseTimeStamp(ImageView imageView, String imageB64) {
        if(imageB64 == null){
            imageView.setImageResource(R.drawable.placeholder);
        } else {
            imageView.setImageBitmap(decodeFromImg64ToBitmap(imageB64));
        }
    }

    @BindingAdapter("android:textFlag")
    public static void checkStatus(TextView textView, String status){
        String text = "";
        text = status.equals(Constants.TRIP_CANCELLED)? "Cancelled" : "";
        textView.setText(text);
    }

    public static Bitmap decodeFromImg64ToBitmap(String imageB64) {
        byte[] decodedString = Base64.decode(imageB64, Base64.URL_SAFE);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
