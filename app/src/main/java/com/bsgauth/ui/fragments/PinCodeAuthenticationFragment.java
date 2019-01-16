package com.bsgauth.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bsgauth.R;
import com.bsgauth.authentication.PinAuthenticationPresenter;
import com.bsgauth.navigation.AuthenticationNavigationPresenter;
import com.bsgauth.repositories.PinCodeRepository;
import com.bsgauth.ui.activities.MoveToFragment;
import com.goodiebag.pinview.Pinview;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinCodeAuthenticationFragment extends Fragment {
    private PinAuthenticationPresenter authentication;


    public PinCodeAuthenticationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        authentication = new PinAuthenticationPresenter(new AuthenticationNavigationPresenter(), new PinCodeRepository());

        Toast.makeText(getContext(), R.string.pin_code_request_text, Toast.LENGTH_LONG).show();

        View view = inflater.inflate(R.layout.fragment_pin_code_authentication, container, false);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
            }
        });

        return view;
    }


    public void authenticate() {
        Pinview pinview = Objects.requireNonNull(this.getView()).findViewById(R.id.pin_view);
        String value = pinview.getValue();

            if (value == null || value.length() != 4) {
                Toast.makeText(getActivity(), R.string.pin_code_request_text, Toast.LENGTH_SHORT).show();
            } else {
                authentication.authenticate(Integer.decode(value), (MoveToFragment) getActivity());
                closeKeyboard();
            }
    }
    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
