package com.space.flightmanagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private String dateBirth;

    public Tourist(String name, String surname, Gender gender, String country, String notes, String dateBirth) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.notes = notes;
        this.dateBirth = dateBirth;
    }

    public enum Gender {
        MALE, FEMALE
    }
}