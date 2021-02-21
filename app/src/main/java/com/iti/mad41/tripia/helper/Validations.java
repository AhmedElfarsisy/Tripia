package com.iti.mad41.tripia.helper;

public class Validations {

    public static boolean isEmpty (String s) {
        return s.isEmpty();
    }

    public static boolean isValidPassword(String password) {
        boolean matches = password.matches(Constants.VALIDATION_REGEX_PASS);
        return matches;
    }

    public static boolean isValidEmail(String email) {
        boolean matches = email.matches(Constants.VALIDATION_REGEX_EMIAL);
        return matches;

    }
}
