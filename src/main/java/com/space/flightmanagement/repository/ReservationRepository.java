package com.space.flightmanagement.repository;

import com.space.flightmanagement.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByTourist(Long touristId);

    List<Reservation> findByFlight(Long flightId);
}