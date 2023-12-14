package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class BusRoute {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    @ManyToMany(fetch = FetchType.LAZY , cascade ={ CascadeType.PERSIST ,CascadeType.MERGE})
    private HashSet<BusStop> busStopSet  = new HashSet<>();

}
