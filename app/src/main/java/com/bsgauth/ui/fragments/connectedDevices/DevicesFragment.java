package com.bsgauth.ui.fragments.connectedDevices;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsgauth.R;
import com.bsgauth.model.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: button "Delete" appears in the toolbar when item is selected

public class DevicesFragment extends Fragment implements DevicesDeletable{

    private RecyclerView recyclerView;
    private DevicesRvAdapter adapter;

    private SwitchCompat switchCompat;
    private Toolbar toolbar;
    private View view;

    private ArrayList<Device> devicesList;

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        devicesList = (ArrayList<Device>) Objects.requireNonNull(getArguments()).get("devices");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the layout for this fragment
        view = inflater.inflate(R.layout.fragment_devices, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Initialize toolbar and set appropriate title
        toolbar = view.findViewById(R.id.toolbar_devices);
        toolbar.setSubtitle(Objects.requireNonNull(getArguments()).getCharSequence("serviceName"));

        //initialize RecyclerView
        recyclerView = view.findViewById(R.id.rv_devices);
        recyclerView.setSaveEnabled(true);
        adapter = new DevicesRvAdapter(devicesList, (AppCompatActivity)getActivity(), getContext(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Initialize switch
        switchCompat = view.findViewById(R.id.switch_devices);

    }


    @Override
    public void deleteDevices(List<Device> devices) {
        devicesList.removeAll(devices);

        adapter.notifyDataSetChanged();
    }
}