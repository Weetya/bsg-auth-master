package com.bsgauth.navigation;

import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.homeScreen.HomescreenFragment;

public class EnterCodeManuallyNavigation {

    public void switchToHomeFragment(MoveToFragment move){
        ((MainActivity) move).clearBackStack();
        move.moveTo(new HomescreenFragment());
    }
}
