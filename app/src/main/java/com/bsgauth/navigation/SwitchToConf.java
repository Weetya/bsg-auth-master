package com.bsgauth.navigation;

import android.app.Activity;

import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.ConfirmationFragment;
import com.bsgauth.ui.fragments.homeScreen.HomescreenFragment;

public class SwitchToConf {
    public void switchToConf(MoveToFragment moveTo) {
        moveTo.moveTo(new ConfirmationFragment());
    }

    public void switchToHome(MoveToFragment move){
        ((MainActivity) move).clearBackStack();
        move.moveTo(new HomescreenFragment());
    }
    public void exitApplication(Activity activity) {
        activity.finish();
    }



}



