package com.fiction.neosoft.calculator;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.realm.Realm;

/**
 * Created by neosoft on 20/6/17.
 */

public class CalculatorApplication extends Application {
    private static CalculatorApplication app;
    private SharedPreferences sp;

    public static CalculatorApplication getInstance() {
        return app;
    }

    public SharedPreferences getSp() {
        return sp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Realm.init(getApplicationContext());
    }


}
