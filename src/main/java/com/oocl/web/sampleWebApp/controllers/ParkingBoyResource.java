package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import com.oocl.web.sampleWebApp.models.ParkingBoyWithParkingLotResponse;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @GetMapping
    public ResponseEntity<ParkingBoyResponse[]> getAll() {
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
            .map(ParkingBoyResponse::create)
            .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    @PostMapping
    public ResponseEntity<ParkingBoyResponse[]> add(@RequestBody ParkingBoy parkingBoy) {
        parkingBoyRepository.save(parkingBoy);
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
                .map(ParkingBoyResponse::create)
                .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    @PostMapping("/{employeeId}/parkinglots")
    public ResponseEntity<ParkingLot> associateParkingLotsToParkingBoys(@PathVariable String employeeId, @RequestBody ParkingLot parkingLot) {
        ParkingBoy targetParkingBoy = parkingBoyRepository.findOneByEmployeeId(employeeId);
        ParkingLot targetParkingLot = parkingLotRepository.findOneByParkingLotId(parkingLot.getParkingLotId());
        targetParkingLot.setParkingBoy(targetParkingBoy);
        parkingLotRepository.saveAndFlush(targetParkingLot);
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
                .map(ParkingBoyResponse::create)
                .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(targetParkingLot);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ParkingBoyWithParkingLotResponse> getEmployee(@PathVariable String employeeId) {

        ParkingBoy parkingBoy = parkingBoyRepository.findOneByEmployeeId(employeeId);
        List<ParkingLot> parkingLots = parkingLotRepository.findAllByParkingBoyId(parkingBoy.getId());
        final ParkingBoyWithParkingLotResponse response = ParkingBoyWithParkingLotResponse.create(
                parkingBoy.getEmployeeId(),
                parkingLots.stream().map(pl ->
                        ParkingLotResponse.create(pl.getParkingLotId(), pl.getCapacity())).collect(Collectors.toList())
        );
        return ResponseEntity.ok(response);
    }
}
