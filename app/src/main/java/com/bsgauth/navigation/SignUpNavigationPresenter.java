package com.bsgauth.navigation;

import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.SelectFragment;

public class SignUpNavigationPresenter {
    public void moveToConfirmationChoice(MoveToFragment moveTo) {
        // screens 3-4
        moveTo.moveTo(new SelectFragment());
    }
}
