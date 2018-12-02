package com.oocl.web.sampleWebApp.domain;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_lot_id", length = 64, unique = true, nullable = false)
    private String parkingLotId;

    @Column(name = "capacity", length = 100)
    private Integer capacity;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parking_boy_id")
    private ParkingBoy parkingBoy;

    protected ParkingLot(){}

    public ParkingLot(String parkingLotId, Integer capacity){
        this.parkingLotId = parkingLotId;
        this.capacity = capacity;
    }

    public String getParkingLotId(){
        return this.parkingLotId;
    }

    public Integer getCapacity(){
        return this.capacity;
    }

    public ParkingBoy getParkingBoy(){
        return this.parkingBoy;
    }

    public void setParkingBoy(ParkingBoy parkingBoy){
        this.parkingBoy = parkingBoy;
    }

    public void setParkingLotId(String parkingLotId){
        this.parkingLotId = parkingLotId;
    }

    public void setCapacity(Integer capacity){
        this.capacity = capacity;
    }
}
