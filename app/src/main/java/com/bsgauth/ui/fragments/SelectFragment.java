package com.bsgauth.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsgauth.R;
import com.bsgauth.navigation.SwitchToConf;
import com.bsgauth.ui.activities.MoveToFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectFragment extends Fragment {

    private SwitchToConf navigation;
    public SelectFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select, container, false);
        navigation = new SwitchToConf();

        view.findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToConf();
            }
        });
        view.findViewById(R.id.mes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToConf();
            }
        });
        return view;
    }

    public void switchToConf() {
        navigation.switchToConf((MoveToFragment) getActivity());
    }
}
