package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class BusRoute {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    @ManyToMany(fetch = FetchType.LAZY , cascade ={ CascadeType.PERSIST ,CascadeType.MERGE})
    private HashSet<BusStop> busStopSet  = new HashSet<>();

    public HashSet<BusStop> getBusStopSet() {
        return busStopSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusStopSet(HashSet<BusStop> busStopSet) {
        this.busStopSet = busStopSet;
    }
}
