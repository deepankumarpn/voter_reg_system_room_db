package io.deepankumarpn.voteridregistration.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.Calendar;

import io.deepankumarpn.voteridregistration.data.local.SharedPreferencesManager;
import io.deepankumarpn.voteridregistration.ui.login.LoginActivity;

public class Utils {

    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches("^(\\+91)[1-9]\\d{9}$|^[0][1-9]\\d{9}$|^[1-9]\\d{9}$", phoneNumber);
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    public static boolean isNameValid(String password) {
        return password.length() >= 3;
    }

    public static void loginAgain(Context context) {
        new SharedPreferencesManager(context).clear();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean is18AndAbove(Date dob) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate birthDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            return Period.between(birthDate, currentDate).getYears() >= 18;
        }
        return false;
    }

    public static String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "" + day + "-" + month + "-" + year;
    }

    public static String beautifyDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
