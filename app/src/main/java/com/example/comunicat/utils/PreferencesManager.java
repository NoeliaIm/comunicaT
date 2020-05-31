package com.example.comunicat.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private final String PREFS_NAME = "prefs_comunicat";
    private Context ctx;

    public PreferencesManager( Context ctx){
        this.ctx= ctx;
    }

    public boolean getPref(String key){
        SharedPreferences preferences= ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public void setPref(String key, boolean value){
        SharedPreferences.Editor editor= ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
