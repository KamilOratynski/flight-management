package com.space.flightmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "departure")
    private Date departure;

    @Column(name = "arrival")
    private Date arrival;

    @Column(name = "number_seats")
    private int numberSeats;

    @ManyToMany(mappedBy = "flights")
    private List<Tourist> tourists = new ArrayList<>();

    @Column(name = "ticket_price")
    private double ticketPrice;

    public Flight(Date departure, Date arrival, int numberSeats, double ticketPrice) {
        this.departure = departure;
        this.arrival = arrival;
        this.numberSeats = numberSeats;
        this.ticketPrice = ticketPrice;
    }
}