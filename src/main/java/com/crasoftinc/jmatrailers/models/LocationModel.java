package com.crasoftinc.jmatrailers.models;

import javax.validation.constraints.NotNull;

public class LocationModel {
    @NotNull(message = "Latitude should not be null")
    private String latitude;
    @NotNull(message = "Longitude should not be null")
    private String longitude;

    public LocationModel(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
