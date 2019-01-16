package com.bsgauth.ui.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.bsgauth.R;
import com.bsgauth.addNewAccount.Presenter.PhoneNumberPresenter;
import com.bsgauth.navigation.SignUpNavigationPresenter;
import com.bsgauth.repositories.CurrentUserRepository;
import com.bsgauth.ui.activities.MoveToFragment;
import com.santalu.maskedittext.MaskEditText;


public class PhoneNumberFragment extends Fragment{
    private SignUpNavigationPresenter navigation;
    private PhoneNumberPresenter presenter;
    private MaskEditText etPhoneNumber;
    private String lastChar = " ";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navigation = new SignUpNavigationPresenter();
        presenter = new PhoneNumberPresenter(getActivity(), new CurrentUserRepository());

        View view = inflater.inflate(R.layout.fragment_phone_number, container, false);

        etPhoneNumber = view.findViewById(R.id.et_phone);
        String phone = etPhoneNumber.getRawText();
        presenter.savePhoneNumber(phone);
        view.findViewById(R.id.submission_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.moveToConfirmationChoice((MoveToFragment) getActivity());
            }
        });

        return view;
    }
}