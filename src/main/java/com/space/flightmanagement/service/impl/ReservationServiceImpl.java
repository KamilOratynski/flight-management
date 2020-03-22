package com.space.flightmanagement.service.impl;

import com.space.flightmanagement.model.Reservation;
import com.space.flightmanagement.repository.ReservationRepository;
import com.space.flightmanagement.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService<Reservation> {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Long flightId, Long touristId) {
        Reservation reservation = new Reservation();
        reservation.setFlight(flightId);
        reservation.setTourist(touristId);
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByTourist(Long touristId) {
        return reservationRepository.findByTourist(touristId);
    }

    @Override
    public List<Reservation> findByFlight(Long flightId) {
        return reservationRepository.findByFlight(flightId);
    }
}