package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import jakarta.persistence.*;
import org.geolatte.geom.Geometry;

@Entity
public class ValleyStreet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Column(columnDefinition = "wkb_geometry")
    private byte[] geometry;



    public void setId(Long id) {

        this.Id = id;
    }

    public Long getId() {
        return Id;
    }
}
