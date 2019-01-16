package com.bsgauth.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bsgauth.R;
import com.bsgauth.navigation.AddNewAccountNavigation;
import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.activities.ShowPopupListener;


public class AddNewAccountFragment extends Fragment implements DialogInterface.OnDismissListener{

    AddNewAccountNavigation navigation;
    ShowPopupListener listenerPopup;

    public AddNewAccountFragment (){
        // Required empty public constructor
    }

    public void setShowPopupListener(ShowPopupListener listener) {
        this.listenerPopup = listener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navigation = new AddNewAccountNavigation();

        View view = inflater.inflate(R.layout.fragment_add_new_account, container, false);
        Button scanQrBtn = view.findViewById(R.id.scanQR_btn);
        final Button enterCodeBtn = view.findViewById(R.id.enter_code_btn);

        scanQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQRCode();
            }
        });

        enterCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterCodeManually();
            }
        });

        return view;
    }

    public void showSuccessPopup(){
        if(listenerPopup != null){
            listenerPopup.showPopup(R.string.your_code_is_successful, R.drawable.ic_on_success, this);
        }
    }

    private void scanQRCode (){
        navigation.switchToQRCodeScanner((MoveToFragment) getActivity());
    }

    private void enterCodeManually () {
        navigation.switchToSoftToken((MoveToFragment) getActivity());
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        navigation.switchToHomeScreen((MoveToFragment) getActivity());
    }
}
