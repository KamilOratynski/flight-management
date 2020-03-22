package com.space.flightmanagement.data;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.repository.TouristRepository;
import com.space.flightmanagement.service.impl.FlightServiceImpl;
import com.space.flightmanagement.service.impl.TouristServiceImpl;
import org.springframework.boot.ApplicationRunner;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ExampleData {

    private SecureRandom random = new SecureRandom();
    private Faker faker = new Faker(new Locale("en"));
    private SimpleDateFormat dataFormatBirthday = new SimpleDateFormat("dd-MM-yyyy");
    private SimpleDateFormat dataFormatFlight = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public ApplicationRunner addTouristAndFlightToDatabase(FlightServiceImpl flightService, TouristServiceImpl touristService) {
        return args -> {
            for (int i = 0; i < 1000; i++) {
                touristService.save(
                        new Tourist(
                                faker.name().firstName(),
                                faker.name().lastName(),
                                randomEnum(Tourist.Gender.class),
                                faker.country().name(),
                                faker.pokemon().name(),
                                dataFormatBirthday.format(faker.date().birthday(18, 65))));
                flightService.save(
                        new Flight(
                                dataFormatFlight.format(faker.date().birthday(0, 1)),
                                dataFormatFlight.format(faker.date().birthday(0, 1)),
                                faker.number().numberBetween(5, 10),
                                faker.number().randomDouble(2, 50, 200)));
            }
        };
    }
}