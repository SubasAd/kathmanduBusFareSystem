package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double Longitute;
    private Double Latitude;
    private  String name;

    public void setLongitute(Double longitute) {
        Longitute = longitute;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitute() {
        return Longitute;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
