package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class BusRoute {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(cascade = {CascadeType.ALL,CascadeType.MERGE},mappedBy = "busRouteSet")
    @JsonIgnore
    private Set<BusStop> busStopSet  = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }


    public Set<BusStop> getBusStopSet() {
        return busStopSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusStopSet(Set<BusStop> busStopSet) {
        this.busStopSet = busStopSet;
    }
}
