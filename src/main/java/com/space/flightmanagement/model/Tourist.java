package com.space.flightmanagement.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(exclude = "flights")
@Entity
@Table(name = "tourist")
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourist_id")
    private Long touristId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "country")
    private String country;

    @Column(name = "notes")
    private String notes;

    @Column(name = "date_birth")
    private Date dateBirth;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tourist_flight",
            joinColumns = @JoinColumn(name = "tourist_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id"))
    private List<Flight> flights;

    public Tourist(String name, String surname, Gender gender, String country, String notes, Date dateBirth, Flight... flights) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.notes = notes;
        this.dateBirth = dateBirth;
        this.flights = Stream.of(flights).collect(Collectors.toList());
        this.flights.forEach(x -> x.getTourists().add(this));
    }

    public enum Gender {
        MALE, FEMALE
    }
}