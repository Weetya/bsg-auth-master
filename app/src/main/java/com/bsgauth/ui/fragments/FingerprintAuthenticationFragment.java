package com.bsgauth.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bsgauth.R;
import com.bsgauth.navigation.AuthenticationNavigationPresenter;
import com.bsgauth.ui.activities.MoveToFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class FingerprintAuthenticationFragment extends Fragment {
    private AuthenticationNavigationPresenter navigation;


    public FingerprintAuthenticationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navigation = new AuthenticationNavigationPresenter();

        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(getContext());
        if (fingerprintManager.isHardwareDetected()) {

            fingerprintManager.authenticate(null, 0, null, new FingerprintManagerCompat.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    fingerprintUnavailable();
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    try {
                        navigation.authenticate((MoveToFragment) getActivity());
                    }
                    catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }, null);

            Toast.makeText(getContext(), R.string.fingerprint_request_text, Toast.LENGTH_LONG).show();

            View view = inflater.inflate(R.layout.fragment_fingerprint_authentication, container, false);
            view.findViewById(R.id.switch_to_pin_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchToPin();
                }
            });

            return view;
        }
        else {
            fingerprintUnavailable();
            return null;
        }
    }



    public void switchToPin() {
        navigation.switchToPinAuth((MoveToFragment) getActivity());
    }


    private void fingerprintUnavailable() {
        Toast.makeText(getContext(), R.string.fingerprint_unavailable_text, Toast.LENGTH_LONG).show();
        navigation.switchToPinAuth((MoveToFragment) getActivity());
    }
}