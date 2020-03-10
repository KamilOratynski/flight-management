package com.space.flightmanagement.service.impl;

import com.google.common.collect.Lists;
import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.repository.FlightRepository;
import com.space.flightmanagement.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements CrudService<Flight> {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findAll() {
        return Lists.newArrayList(flightRepository.findAll());
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Long delete(Long id) {
        flightRepository.deleteById(id);
        return id;
    }

    @Override
    public Flight update(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getById(Long id) {
        return flightRepository.findById(id);
    }
}