package com.space.flightmanagement.repository;

import com.space.flightmanagement.model.Tourist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends CrudRepository<Tourist, Long> {
}