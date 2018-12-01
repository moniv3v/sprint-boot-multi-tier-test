package com.oocl.web.sampleWebApp.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parking_boy_id", length = 64, unique = true, nullable = false)
    private String parkingLotId;

    @Column(name = "capacity", length = 100)
    private Integer capacity;

    protected ParkingLot(){}

    public ParkingLot(String parkingLotId, Integer capacity){
        this.parkingLotId = parkingLotId;
        this.capacity = capacity;
    }

    public String getParkingLotId(){
        return this.parkingLotId;
    }

    public Integer getCapacity(){
        return this.getCapacity();
    }

    public void setParkingLotId(String parkingLotId){
        this.parkingLotId = parkingLotId;
    }

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }
}
