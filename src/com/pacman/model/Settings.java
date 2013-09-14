package com.pacman.model;

import com.pacman.MainActivity;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private SharedPreferences sharedPref;
    public Settings(MainActivity activity) {
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    }
    
    public int get(String string){
        return sharedPref.getInt(string, 0);
    }
    
    public void setConfig(String string, int value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(string, value);
        editor.commit();
    }
}

