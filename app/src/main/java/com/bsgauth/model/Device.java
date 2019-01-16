package com.bsgauth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/***
 * @author Daria Kostenko
 */
public class Device implements Serializable {

    private DeviceType deviceType;
    private String latestUse;
    private String deviceModel;

    public Device() {
    }

    public Device(DeviceType deviceType, String latestUse, String deviceModel) {
        this.deviceType = deviceType;
        this.latestUse = latestUse;
        this.deviceModel = deviceModel;
    }

    @JsonProperty("type")
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @JsonProperty("type")
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @JsonProperty("date")
    public String getLatestUse() {
        return latestUse;
    }

    @JsonProperty("date")
    public void setLatestUse(String latestUse) {
        this.latestUse = latestUse;
    }

    @JsonProperty("name")
    public String getDeviceModel() {
        return deviceModel;
    }

    @JsonProperty("name")
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

}
