package com.bsgauth.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/***
 *  Created by Daria Kostenko
 */
public class Account {

    private int totp;
    private String websiteName;
    private List<Device> connectedDevices;

    public Account() {
    }

    public Account(int totp, String websiteName, ArrayList<Device> connectedDevices) {
        this.totp = totp;
        this.websiteName = websiteName;
        this.connectedDevices = connectedDevices;
    }

    @JsonProperty("totp_code")
    public int getTotp() {
        return totp;
    }

    @JsonProperty("totp_code")
    public void setTotp(int totp) {
        this.totp = totp;
    }

    @JsonProperty("service")
    public String getWebsiteName() {
        return websiteName;
    }

    @JsonProperty("service")
    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    @JsonProperty("devices")
    public List<Device> getConnectedDevices() {
        return connectedDevices;
    }

    @JsonProperty("devices")
    public void setConnectedDevices(List<Device> connectedDevices) {
        this.connectedDevices = connectedDevices;
    }

}
