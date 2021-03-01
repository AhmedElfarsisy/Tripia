package com.iti.mad41.tripia.helper;

import com.iti.mad41.tripia.ui.fragment.form.FormFragment;

public class Constants {
    //    public static final  String  ="";
    public static final String SIGNIN_FRAGMENT = "SigninFragment";
    public static final String SIGNUP_FRAGMENT = "SignupFragment";
    public static final String MORE_FRAGMENT = "MoreFragment";
    public static final String PROFILE_FRAGMENT = "ProfileFragment";
    public static final String FORM_FRAGMENT = "FormFragment";

    public static String VALIDATION_REGEX_PASS = "\\b[a-zA-Z0-9\\-._]{6,}\\b";
    public static String VALIDATION_REGEX_EMIAL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static String VALIDATION_REGEX_USER = "\\b[a-zA-Z][a-zA-Z0-9\\-._]{6,}\\b";
    public static final String TRIP_Firebase_ID_KEY = "tripId";

    public static final String TRIP_ID_KEY = "tripId";
    public static final String TRIP_TITLE_KEY = "tripTitle";
    public static final String TRIP_DATE_KEY = "tripDate";
    public static final String TRIP_START_LAT_KEY = "tripStartLat";
    public static final String TRIP_START_Log_KEY = "tripStartLog";
    public static final String TRIP_DESTINATION_Log_KEY = "tripDestinationLog";
    public static final String TRIP_DESTINATION_Lat_KEY = "tripDestinationLat";
    public static final String TRIP_START_ADDRESS_KEY = "tripStartAddress";
    public static final String TRIP_DESTINATION_ADDRESS_KEY = "tripDestinationAddress";


    public static final String TRIP_FINISHED = "Finish";
    public static final String TRIP_CANCELLED = "Cancel";
    public static final String TRIP_RUNNING = "Run";


    public static final String PROFILE_IMAGE_KEY = "profileImage";

    public static final int GALLERY_PERMISSION_REQUSET_CODE = 2;
    public static final int GAllery_INTENT_REQUSET_CODE = 2;
    public static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 102;






    public static final String REPEATE_DAILY = "daily";
    public static final String REPEATE_MONTHLY = "monthly";

}
