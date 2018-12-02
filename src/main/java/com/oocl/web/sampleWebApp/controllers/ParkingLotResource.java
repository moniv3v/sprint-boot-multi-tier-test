package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotResource {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @PostMapping
    public ResponseEntity<ParkingLotResponse[]> add(@RequestBody ParkingLot parkingLot) {
        parkingLotRepository.save(parkingLot);
        final ParkingLotResponse[] parkingLots = parkingLotRepository.findAll().stream()
                .map(ParkingLotResponse::create)
                .toArray(ParkingLotResponse[]::new);
        return ResponseEntity.ok(parkingLots);
    }

//    @GetMapping
//    public ResponseEntity<ParkingLotResponse[]> getAll() {
//        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
//                .map(ParkingBoyResponse::create)
//                .toArray(ParkingBoyResponse[]::new);
//        return ResponseEntity.ok(parkingBoys);
//    }
}
