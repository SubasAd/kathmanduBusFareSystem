package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LocationKey implements Serializable {
    private Double longitude;
    private Double latitude;

    public LocationKey(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationKey() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationKey location = (LocationKey) o;
        return Objects.equals(longitude, location.longitude) && Objects.equals(latitude, location.latitude) ;
    }


    public boolean equals(LocationKey o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationKey location =  (LocationKey) o;
        return Objects.equals(longitude, location.longitude) && Objects.equals(latitude, location.latitude) ;
    }
}
