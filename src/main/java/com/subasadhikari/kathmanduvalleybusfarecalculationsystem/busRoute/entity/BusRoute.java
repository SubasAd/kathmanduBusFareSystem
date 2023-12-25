package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busRoute.entity;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity.BusStop;
import com.subasadhikari.kathmanduvalleybusfarecalculationsystem.location.Embeddable.LocationKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @ManyToMany(cascade = {CascadeType.ALL,CascadeType.MERGE})
    private Set<BusStop> busStopSet  = new HashSet<>();
    @ElementCollection(fetch = FetchType.LAZY)
    private List<LocationKey> locations = new ArrayList<>();
}
