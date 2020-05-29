package com.example.ispy;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Context;
import android.content.SharedPreferences.Editor;

    public class Session {

        private SharedPreferences prefs;
        // Editor for Shared preferences
        Editor editor;

        public Session(Context cntx) {
            // TODO Auto-generated constructor stub
            prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
            editor = prefs.edit();
        }

        public void setusename(String usename) {
            editor.putString("ParentMail", usename).commit();

        }

        public String getusename() {
            String usename = prefs.getString("ParentMail","");
            return usename;
        }
    }

