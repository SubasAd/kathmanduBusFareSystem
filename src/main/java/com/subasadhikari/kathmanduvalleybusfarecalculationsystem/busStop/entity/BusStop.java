package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double longitute;
    private Double latitude;
    private  String name;
    @ManyToMany
    private Set<BusRoute> busRouteSet = new HashSet<>();

    public void setLongitute(Double longitute) {
        longitute = longitute;
    }

    public void setLatitude(Double latitude) {
        latitude = latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BusRoute> getBusRouteSet() {
        return busRouteSet;
    }

    public void setBusRouteSet(Set<BusRoute> busRouteSet) {
        this.busRouteSet = busRouteSet;
    }

    public Double getLongitute() {
        return longitute;
    }

    public Double getLatitude() {
        return latitude;
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
