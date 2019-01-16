package com.bsgauth.permissions;

import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class PermissionHandler implements CheckHasPermission, RequestPermission{
    @Override
    public boolean hasPermission(AppCompatActivity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) ==
                PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestPermission(Fragment fragment, String[] permissions, int requestCode) {
        fragment.requestPermissions(permissions, requestCode);
    }

}