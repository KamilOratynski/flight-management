package com.space.flightmanagement.data;

import com.github.javafaker.Faker;
import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.repository.FlightRepository;
import com.space.flightmanagement.repository.TouristRepository;
import org.springframework.boot.ApplicationRunner;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class Database {

    private Faker faker = new Faker(new Locale("en"));

    public Tourist buildTourist() {
        Long l = 0L;
        return Tourist.builder()
                .touristId(l++)
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .gender("male")
                .country(faker.country().name())
                .notes(faker.pokemon().name())
                .dateBirth(faker.date().birthday(18, 65))
                .build();
    }

    public Flight buildFlight() {
        Long k = 0L;
        return Flight.builder()
                .flightId(k++)
                .departure(faker.date().between(Date.from(Instant.now()), Date.from(Instant.now())))
                .arrival(faker.date().between(Date.from(Instant.now()), Date.from(Instant.now())))
                .numberSeats(faker.number().numberBetween(1, 10))
                .ticketPrice(faker.number().randomDouble(2, 2, 43))
                .build();
    }

    public ApplicationRunner addTouristToDatabase(TouristRepository touristRepository) {
        return args -> {
            for (int i = 0; i < 1000; i++) {
                touristRepository.save(buildTourist());
            }
        };
    }

    public ApplicationRunner addFlightToDatabase(FlightRepository flightRepository) {
        return args -> {
            for (int j = 0; j < 1000; j++) {
                flightRepository.save(buildFlight());
            }
        };
    }
}