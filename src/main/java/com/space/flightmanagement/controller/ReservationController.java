package com.space.flightmanagement.controller;

import com.space.flightmanagement.model.Reservation;
import com.space.flightmanagement.service.impl.ReservationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> makeReservation(Long flightId, Long touristId) {
        Reservation reservation = reservationService.save(flightId, touristId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping(value = "/tourist/{touristId}")
    public ResponseEntity<List<Reservation>> findByTourist(@PathVariable(name = "touristId") Long id) {
        List<Reservation> byTourist = reservationService.findByTourist(id);
        return new ResponseEntity<>(byTourist, HttpStatus.OK);
    }

    @GetMapping(value = "/flight/{flightId}")
    public ResponseEntity<List<Reservation>> findByFlight(@PathVariable(name = "flightId") Long id) {
        List<Reservation> byFlight = reservationService.findByFlight(id);
        return new ResponseEntity<>(byFlight, HttpStatus.OK);
    }
}