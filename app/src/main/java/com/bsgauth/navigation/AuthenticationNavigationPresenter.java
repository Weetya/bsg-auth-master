package com.bsgauth.navigation;

import android.app.Activity;

import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.PinCodeAuthenticationFragment;
import com.bsgauth.ui.fragments.homeScreen.HomescreenFragment;

public class AuthenticationNavigationPresenter {
    public void switchToPinAuth(MoveToFragment moveTo) {
        moveTo.moveTo(new PinCodeAuthenticationFragment());
    }

    public void exitApplication(Activity activity) {
        activity.finish();
    }

    public void authenticate(MoveToFragment moveTo) {
        ((MainActivity) moveTo).clearBackStack();
        moveTo.moveTo(new HomescreenFragment());
    }
}