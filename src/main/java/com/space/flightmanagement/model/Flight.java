package com.space.flightmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int numberSeats;
    @ManyToMany
    private List<Tourist> touristList;
    private BigDecimal ticketPrice;

}