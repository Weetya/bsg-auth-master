package com.bsgauth.navigation;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bsgauth.repositories.CurrentUserRepository;
import com.bsgauth.ui.fragments.ConfirmationFragment;
import com.bsgauth.ui.fragments.FingerprintAuthenticationFragment;
import com.bsgauth.ui.fragments.PhoneNumberFragment;
import com.bsgauth.ui.fragments.PinCodeAuthenticationFragment;
import com.bsgauth.ui.fragments.SelectFragment;

public class StartFragmentResolver {
    public Fragment getStartFragment(Context context) {
        return new ConfirmationFragment();
    }
}
