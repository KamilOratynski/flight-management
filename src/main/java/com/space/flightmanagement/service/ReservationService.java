package com.space.flightmanagement.service;

import com.space.flightmanagement.model.Reservation;

import java.util.List;

public interface ReservationService<T> {

    T save(Long flightId, Long touristId);

    List<Reservation> findByTourist(Long touristId);

    List<Reservation> findByFlight(Long flightId);
}