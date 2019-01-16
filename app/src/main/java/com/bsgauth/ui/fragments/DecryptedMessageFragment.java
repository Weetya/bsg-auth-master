package com.bsgauth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bsgauth.R;
import com.bsgauth.addNewAccount.DecryptedMessagePresenter;

public class DecryptedMessageFragment extends Fragment {
    DecryptedMessagePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = new DecryptedMessagePresenter();

        View view = inflater.inflate(R.layout.fragment_decrypted_message, container,
                false);

        TextView passwordTextView = view.findViewById(R.id.one_time_pass);
        Button btnOK = view.findViewById(R.id.btn_ok);

        passwordTextView.setText(this.getPassword());
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this button does something
            }
        });
        return view;
    }

    private String getPassword(){
        return presenter.getDecryptedOneTimePassword();
    }
}
