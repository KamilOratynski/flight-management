package com.space.flightmanagement.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    List<T> findAll();

    T save(T t);

    Long delete(Long id);

    T update(T t);

    Optional<T> getById(Long id);
}