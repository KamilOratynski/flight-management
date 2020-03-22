package com.space.flightmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "departure")
    private String departure;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "number_seats")
    @Range(max = 10, message = "The number of passengers cannot exceed 10")
    private int numberSeats;

    @Column(name = "ticket_price")
    private double ticketPrice;

    public Flight(String departure, String arrival, int numberSeats, double ticketPrice) {
        this.departure = departure;
        this.arrival = arrival;
        this.numberSeats = numberSeats;
        this.ticketPrice = ticketPrice;
    }
}