package com.bsgauth.navigation;

import android.os.Bundle;

import com.bsgauth.model.Account;
import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.AddNewAccountFragment;
import com.bsgauth.ui.fragments.PhoneNumberFragment;
import com.bsgauth.ui.fragments.connectedDevices.DevicesFragment;

import java.util.ArrayList;

public class HomeNavigationPresenter {
    public void moveToConnectedDevices (MoveToFragment moveTo, Account account) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("devices", new ArrayList<>(account.getConnectedDevices()));
        bundle.putCharSequence("serviceName", account.getWebsiteName());

        DevicesFragment fragment = new DevicesFragment();
        fragment.setArguments(bundle);

        moveTo.moveTo(fragment);
    }

    public void moveToAddNewAccount(MoveToFragment moveTo){
        moveTo.moveTo(new AddNewAccountFragment());
    }

    public void backtoFirstFragment(MoveToFragment moveToFragment) {
        ((MainActivity) moveToFragment).clearBackStack();
        moveToFragment.moveTo(new PhoneNumberFragment());
    }


}
