package com.easysawari.ridebuddy.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }
    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }
    public void setuseemail(String useemail) {
        prefs.edit().putString("useemail", useemail).commit();
    }

    public String getuseemail() {
        String useemail = prefs.getString("useemail","");
        return useemail;
    }
    public void setusetype(String usetype) {
        prefs.edit().putString("usetype", usetype).commit();
    }

    public String getusetype() {
        String usetype = prefs.getString("usetype","");
        return usetype;
    }
}
