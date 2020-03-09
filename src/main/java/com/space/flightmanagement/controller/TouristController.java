package com.space.flightmanagement.controller;

import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.service.impl.TouristServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourist")
public class TouristController {

    private final TouristServiceImpl touristService;

    public TouristController(TouristServiceImpl touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public ResponseEntity<List<Tourist>> getAll() {
        List<Tourist> allTourist = touristService.findAll();
        return new ResponseEntity<>(allTourist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tourist> insertTourist(@RequestBody Tourist tourist) {
        Tourist newTourist = touristService.save(tourist);
        return newTourist != null ?
                new ResponseEntity<>(newTourist, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteTourist(@PathVariable(name = "id") Long id) {
        return touristService.getById(id)
                .map(tourist -> {
                    touristService.delete(id);
                    return new ResponseEntity<>(id, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(id, HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Tourist> updateTourist(@PathVariable(name = "id") Long id, @RequestBody Tourist tourist) {
        Tourist updateTourist = touristService.update(tourist);
        return updateTourist != null ?
                new ResponseEntity<>(updateTourist, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}