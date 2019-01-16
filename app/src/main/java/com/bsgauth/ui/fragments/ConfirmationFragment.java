package com.bsgauth.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.bsgauth.R;
import com.bsgauth.addNewAccount.Presenter.PinPresenter;
import com.bsgauth.navigation.SwitchToConf;
import com.bsgauth.repositories.CurrentUserRepository;
import com.bsgauth.repositories.SMSRepository;
import com.bsgauth.ui.activities.MoveToFragment;
import com.goodiebag.pinview.Pinview;

import java.util.Objects;


public class ConfirmationFragment extends Fragment {
    private PinPresenter authentication;
    public ConfirmationFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        authentication = new PinPresenter(new SwitchToConf(), new SMSRepository(), new CurrentUserRepository());

        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        Button confirm = view.findViewById(R.id.conf);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               authenticate();
            }
        });

        return view;
    }

    public void authenticate() {
        Pinview pinview = Objects.requireNonNull(this.getView()).findViewById(R.id.pin);
        String value = pinview.getValue();
        if(value == null || value.length() != 4){
            Toast.makeText(getActivity(), R.string.pin_code_request_text, Toast.LENGTH_SHORT).show();
        }else{
            boolean isSuccessfull = authentication.authenticate(getActivity(), Integer.decode(value), (MoveToFragment) getActivity());
            if(!isSuccessfull){
                Toast.makeText(getActivity(), R.string.enter_correct_code_toast, Toast.LENGTH_SHORT).show();
                closeKeyboard();
            }
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