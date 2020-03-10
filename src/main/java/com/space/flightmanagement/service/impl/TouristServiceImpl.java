package com.space.flightmanagement.service.impl;

import com.google.common.collect.Lists;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.repository.TouristRepository;
import com.space.flightmanagement.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristServiceImpl implements CrudService<Tourist> {

    private final TouristRepository touristRepository;

    public TouristServiceImpl(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @Override
    public List<Tourist> findAll() {
        return Lists.newArrayList(touristRepository.findAll());
    }

    @Override
    public Tourist save(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    @Override
    public Long delete(Long id) {
        touristRepository.deleteById(id);
        return id;
    }

    @Override
    public Tourist update(Tourist tourist) {
        return touristRepository.save(tourist);
    }

    @Override
    public Optional<Tourist> getById(Long id) {
        return touristRepository.findById(id);
    }
}