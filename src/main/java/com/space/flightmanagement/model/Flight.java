package com.space.flightmanagement.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Flight {

    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int numberSeats;
    private List<Tourist> touristList;
    private BigDecimal ticketPrice;

}