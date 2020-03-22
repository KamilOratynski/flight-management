package com.space.flightmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "flight")
    private Long flight;

    @Column(name = "tourist")
    private Long tourist;
}