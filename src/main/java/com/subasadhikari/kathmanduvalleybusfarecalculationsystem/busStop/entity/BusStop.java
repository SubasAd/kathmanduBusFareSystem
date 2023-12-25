package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity.BusRoute;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusStop  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private  String name;
    @ManyToMany(cascade = {CascadeType.ALL,CascadeType.MERGE},mappedBy = "busStopSet")
    @JsonIgnore
    private Set<BusRoute> busRouteSet = new HashSet<>();

    @NonNull
    private LocationKey location;

}
