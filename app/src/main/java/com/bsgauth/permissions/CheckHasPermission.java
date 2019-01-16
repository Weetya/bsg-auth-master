package com.bsgauth.permissions;

import android.support.v7.app.AppCompatActivity;

public interface CheckHasPermission {
    boolean hasPermission(AppCompatActivity activity, String permission);
}
