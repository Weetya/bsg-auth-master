package com.bsgauth.navigation;

import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.EnterCodeManuallyFragment;
import com.bsgauth.ui.fragments.QRScannerFragment;
import com.bsgauth.ui.fragments.homeScreen.HomescreenFragment;

public class AddNewAccountNavigation {

    public void switchToQRCodeScanner(MoveToFragment move) {
        QRScannerFragment fragment = new QRScannerFragment();
        // will show token saved successfully of not
        fragment.setShowPopupListener( (MainActivity)move );
        move.moveTo(fragment);
    }

    public void switchToSoftToken(MoveToFragment move) {
        move.moveTo(new EnterCodeManuallyFragment());
    }

    public void switchToHomeScreen(MoveToFragment move){
        ((MainActivity)move).clearBackStack();
        move.moveTo(new HomescreenFragment());
    }

}
