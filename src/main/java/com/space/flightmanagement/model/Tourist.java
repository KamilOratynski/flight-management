package com.space.flightmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private String country;
    private String notes;
    private LocalDate dateBirth;
    @ManyToMany
    private List<Flight> flightList;

}