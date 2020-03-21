package com.space.flightmanagement.service.impl;

import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.model.Reservation;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation save(Flight flight, Tourist tourist) {
        Reservation reservation = new Reservation();
        reservation.setFlight_id(flight.getFlightId());
        reservation.setTourist_id(tourist.getTouristId());
        return reservationRepository.save(reservation);
    }
}
