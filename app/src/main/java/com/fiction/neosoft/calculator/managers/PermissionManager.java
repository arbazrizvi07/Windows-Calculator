package com.fiction.neosoft.calculator.managers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.fiction.neosoft.calculator.CalculatorApplication;

public class PermissionManager {

    public static boolean askForPermission() {

        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean hasStoragePermission() {

        return ContextCompat.checkSelfPermission(CalculatorApplication.getInstance(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(CalculatorApplication.getInstance(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasVibratePermission() {

        return ContextCompat.checkSelfPermission(CalculatorApplication.getInstance(),
                Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasInternetPermission() {

        return ContextCompat.checkSelfPermission(CalculatorApplication.getInstance(),
                Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean hasPhoneStatePermission() {

        return ContextCompat.checkSelfPermission(CalculatorApplication.getInstance(),
                Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
    }


}