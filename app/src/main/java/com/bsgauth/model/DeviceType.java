package com.bsgauth.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeviceType {
    COMPUTER,
    @JsonProperty("smartphone")
    SMARTPHONE,
    @JsonProperty("tablet")
    TABLET;
}
