package com.space.flightmanagement.data;

import com.github.javafaker.Faker;
import com.space.flightmanagement.model.Flight;
import com.space.flightmanagement.model.Tourist;
import com.space.flightmanagement.repository.TouristRepository;
import org.springframework.boot.ApplicationRunner;

import java.security.SecureRandom;
import java.util.Locale;

public class ExampleData {

    private SecureRandom random = new SecureRandom();
    private Faker faker = new Faker(new Locale("en"));

    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public ApplicationRunner addTouristToDatabase(TouristRepository touristRepository) {
        return args -> {
            for (int i = 0; i < 500; i++) {
                touristRepository.save(new Tourist(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        randomEnum(Tourist.Gender.class),
                        faker.country().name(),
                        faker.pokemon().name(),
                        faker.date().birthday(18, 65),
                        new Flight(
                                faker.date().birthday(),
                                faker.date().birthday(),
                                faker.number().numberBetween(1, 10),
                                faker.number().randomDouble(2, 2, 43))));
            }
        };
    }
}