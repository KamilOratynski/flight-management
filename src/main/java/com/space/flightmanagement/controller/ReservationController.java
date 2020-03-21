package com.space.flightmanagement.controller;

import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.model.Reservation;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.service.impl.ReservationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/reservation")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> makeReservation(Flight flight, Tourist tourist) {
        Reservation reservation = reservationService.save(flight, tourist);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}


//        Reservation newReservation = new Reservation();
//        newReservation.setFlight_id(flight.getFlightId());
//        newReservation.setTourist_id(tourist.getTouristId());
//        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);