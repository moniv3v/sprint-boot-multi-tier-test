package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.Objects;

public class ParkingLotResponse {
    private String parkingLotId;
    private int capacity;

    public String getParkingLotId() {
        return parkingLotId;
    }

    public int getCapacity() {
        return capacity;
    }

    public static ParkingLotResponse create(String parkingLotId, int capacity) {
        final ParkingLotResponse response = new ParkingLotResponse();
        response.parkingLotId = parkingLotId;
        response.capacity = capacity;
        return response;
    }

    @JsonIgnore
    public boolean isValid() {
        return parkingLotId != null;
    }
}
