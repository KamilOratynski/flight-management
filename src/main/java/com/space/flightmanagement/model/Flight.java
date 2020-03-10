package com.space.flightmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tourist_flight",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "tourist_id"))
    private List<Tourist> tourists = new ArrayList<>();

    @Column(name = "ticket_price")
    private double ticketPrice;

    public Flight(String departure, String arrival, int numberSeats, double ticketPrice, List<Tourist> tourists) {
        this.departure = departure;
        this.arrival = arrival;
        this.numberSeats = numberSeats;
        this.ticketPrice = ticketPrice;
        this.tourists = tourists;
    }
}