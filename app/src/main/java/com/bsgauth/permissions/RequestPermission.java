package com.bsgauth.permissions;

import android.support.v4.app.Fragment;

public interface RequestPermission {
    void requestPermission(Fragment fragment, String[] permissions, int requestCode);
}
