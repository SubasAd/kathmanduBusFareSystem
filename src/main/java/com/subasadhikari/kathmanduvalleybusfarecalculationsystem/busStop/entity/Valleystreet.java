package com.subasadhikari.kathmanduvalleybusfarecalculationsystem.busStop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Valleystreet {


    @Id
    private Long ogcFid;
    private byte wkbGeometry;
    private String id;
    private String highway;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte getWkbGeometry() {
        return wkbGeometry;
    }

    public void setWkbGeometry(byte wkbGeometry){
        this.wkbGeometry = wkbGeometry;
    }

    public Long getOgcFid(){
        return ogcFid;
    }

    public void setOgcFid(Long ogcFid) {
        this.ogcFid = ogcFid;
    }
}
