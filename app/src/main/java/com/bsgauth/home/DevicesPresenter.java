package com.bsgauth.home;

import com.bsgauth.model.Device;
import com.bsgauth.repositories.DevicesRepository;
import com.bsgauth.ui.fragments.connectedDevices.DevicesDeletable;

import java.util.List;

public class DevicesPresenter {
    private DevicesRepository repository;

    public DevicesPresenter(DevicesRepository repository) {
        this.repository = repository;
    }

    public void deleteDevices (List<Device> devices, DevicesDeletable devicesDeletable) {
        for (Device device : devices)
            repository.deleteDevice(device);

        devicesDeletable.deleteDevices(devices);
    }

    public void setMultipleDevicesAllowed (boolean multipleDevicesAllowed) {

    }
}
