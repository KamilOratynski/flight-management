package com.space.flightmanagement.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Tourist {

    private String name;
    private String surname;
    private String gender;
    private String country;
    private String notes;
    private LocalDate dateBirth;
    private List<Flight> flightList;

}