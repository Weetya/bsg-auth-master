package com.bsgauth.ui.fragments.connectedDevices;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bsgauth.R;
import com.bsgauth.home.DevicesPresenter;
import com.bsgauth.model.Device;
import com.bsgauth.repositories.DevicesRepository;

import java.util.ArrayList;

public class DevicesRvAdapter extends RecyclerView.Adapter<DevicesRvAdapter.ViewHolder> {
    private ArrayList<Device> devicesList;

    private Boolean multiSelect;
    private ArrayList<Device> selectedDevices;
    private AppCompatActivity activity;
    private Context context;

    private DevicesDeletable devicesDeletable;
    private DevicesPresenter presenter;

    public ArrayList<Device> getSelectedDevices() {
        return selectedDevices;
    }


    public DevicesRvAdapter(ArrayList<Device> devicesList, AppCompatActivity activity, Context context, DevicesDeletable devicesDeletable) {
        this.devicesList = devicesList;
        this.activity = activity;
        this.context = context;
        this.devicesDeletable = devicesDeletable;
        multiSelect = false;
        selectedDevices = new ArrayList<>();

        presenter = new DevicesPresenter(new DevicesRepository());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.update(devicesList.get(position));
        viewHolder.lastUsed.setText(devicesList.get(position).getLatestUse());
        viewHolder.deviceName.setText(devicesList.get(position).getDeviceModel());

        switch (devicesList.get(position).getDeviceType()){

            case COMPUTER: viewHolder.deviceType.setImageResource(R.drawable.ic_screen);
                break;
            case TABLET: viewHolder.deviceType.setImageResource(R.drawable.ic_tablet);
                break;
            case SMARTPHONE: viewHolder.deviceType.setImageResource(R.drawable.ic_smartphone);
                break;
            default: viewHolder.deviceType.setImageResource(R.drawable.ic_wallpaper_black_24dp);

        }
    }

    @Override
    public int getItemCount() {
        return (devicesList != null ? devicesList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView deviceType;
        public TextView lastUsed;
        public TextView deviceName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.cardview_device);
            this.deviceType = itemView.findViewById(R.id.device_type);
            this.lastUsed = itemView.findViewById(R.id.device_last_use);
            this.deviceName = itemView.findViewById(R.id.device_name);
        }

        void update(final Device device){

            if(selectedDevices.contains(device)){
                cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
            } else {
                cardView.setCardBackgroundColor(Color.WHITE);
            }


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((AppCompatActivity)v.getContext()).startSupportActionMode(new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                            multiSelect = true;
                            activity.getMenuInflater().inflate(R.menu.menu_delete, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                            return false;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                            presenter.deleteDevices(selectedDevices, devicesDeletable);

                            actionMode.finish();
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode actionMode) {
                            multiSelect = false;
                            selectedDevices.clear();
                            notifyDataSetChanged();
                        }
                    });
                    selectDevice(device);
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       selectDevice(device);
                }
            });
        }

        void selectDevice(Device device){

            if(multiSelect){
                if(selectedDevices.contains(device)){
                    selectedDevices.remove(device);
                    cardView.setCardBackgroundColor(Color.WHITE);
                }else{
                    selectedDevices.add(device);
                    cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
                }
            }
        }

    }

}
