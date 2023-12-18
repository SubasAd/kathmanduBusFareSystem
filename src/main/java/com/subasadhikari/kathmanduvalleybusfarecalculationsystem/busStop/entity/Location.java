package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Location {
    private Double longitude;
    private Double latitude;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(longitude, location.longitude) && Objects.equals(latitude, location.latitude) ;
    }


    public boolean equals(Location o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location =  (Location) o;
        return Objects.equals(longitude, location.longitude) && Objects.equals(latitude, location.latitude) ;
    }
}
