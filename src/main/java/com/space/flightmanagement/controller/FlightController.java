package com.space.flightmanagement.controller;

import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.service.impl.FlightServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightServiceImpl flightService;

    public FlightController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAll() {
        List<Flight> allFlight = flightService.findAll();
        return new ResponseEntity<>(allFlight, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Flight> insertFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.save(flight);
        return newFlight != null ?
                new ResponseEntity<>(newFlight, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteFlight(@PathVariable(name = "id") Long id) {
        return flightService.getById(id)
                .map(flight -> {
                    flightService.delete(id);
                    return new ResponseEntity<>(id, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(id, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable(name = "id") Long id, @RequestBody Flight flight) {
        Flight updateFlight = flightService.update(flight);
        return updateFlight != null ?
                new ResponseEntity<>(updateFlight, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}