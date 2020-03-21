package com.space.flightmanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Reservation {

    @Id
    private Long id;
    private Long flight_id;
    private Long tourist_id;
}