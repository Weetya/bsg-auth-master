package com.bsgauth.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.bsgauth.R;
import com.bsgauth.addNewAccount.EnterCodeManuallyPresenter;
import com.bsgauth.navigation.EnterCodeManuallyNavigation;
import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MainActivity;
import com.bsgauth.ui.activities.MoveToFragment;
import com.santalu.maskedittext.MaskEditText;

public class EnterCodeManuallyFragment extends Fragment implements DialogInterface.OnDismissListener {
    private String code = null;
    private EnterCodeManuallyPresenter presenter;
    private MaskEditText tokenField;
    private EnterCodeManuallyNavigation navigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new EnterCodeManuallyPresenter();
        navigation = new EnterCodeManuallyNavigation();

        View view = inflater.inflate(R.layout.fragment_enter_code_manually, container, false);
        tokenField = view.findViewById(R.id.soft_token_input);

        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = tokenField.getRawText();
                showStatusPopup();
            }
        });
        return view;
    }

    private void showStatusPopup(){
        if(presenter.isTokenValid(code)){
            this.showPopup(R.string.your_code_is_successful , R.drawable.ic_on_success);
        } else {
            this.showPopup(R.string.token_is_not_valid_text, R.drawable.failure_icon);
        }
    }

    private void showPopup(int messageId, int iconId){
        ((MainActivity)this.getActivity()).showPopup(messageId, iconId, this);
    }
    private void closeKeyboard() {
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        navigation.switchToHomeFragment((MoveToFragment) getActivity());
    }
}
