package com.bsgauth.navigation;
import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.AddNewAccountFragment;

public class QRCodeScannerNavigation {

    public void switchToAddNewAccountFragment(final MoveToFragment moveToFragment){
        // switch to AddNewFragment with listener
        final AddNewAccountFragment fragment = new AddNewAccountFragment();
        fragment.setShowPopupListener((MainActivity) moveToFragment);
        // if token is saved successfully
        fragment.showSuccessPopup();

        moveToFragment.moveTo(fragment);
    }

    public void switchToAddNewFragmentIfNoPermission(BackToFragment backToFragment){
        backToFragment.backTo();
    }
}
