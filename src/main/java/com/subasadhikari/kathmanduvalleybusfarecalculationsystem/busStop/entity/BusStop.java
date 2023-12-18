package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private  String name;
    @ManyToMany(cascade = {CascadeType.ALL,CascadeType.MERGE})
    private Set<BusRoute> busRouteSet = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Location busStopLocation;

    public Location getBusStopLocation() {
        return busStopLocation;
    }

    public void setBusStopLocation(Location busStopLocation) {
        this.busStopLocation = busStopLocation;
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
