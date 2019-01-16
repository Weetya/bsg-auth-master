package com.bsgauth.repositories;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.bsgauth.ui.activities.MainActivity;

public class CurrentUserRepository {
    public boolean isSignedUp (Context context) {
        SharedPreferences preferences = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        boolean isSigned = preferences.getBoolean("isSigned", false);
        return isSigned;
    }

    public void signUp (Context context) {
        SharedPreferences preferences = ((Activity)context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String phone = preferences.getString("prone", "");
        editor.putString("phone", phone);
        editor.putBoolean("isSigned", true);
        editor.apply();
    }

    public void savePhoneNumber(Context context, String phone){
        SharedPreferences preferences = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", phone);
        editor.apply();
    }

    public void logOut(Context context){
        SharedPreferences preferences = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
