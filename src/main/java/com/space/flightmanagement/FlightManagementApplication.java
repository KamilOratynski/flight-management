package com.space.flightmanagement;

import com.space.flightmanagement.data.Database;
import com.space.flightmanagement.repository.FlightRepository;
import com.space.flightmanagement.repository.TouristRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlightManagementApplication {

    private final Database database = new Database();

    public static void main(String[] args) {
        SpringApplication.run(FlightManagementApplication.class, args);
    }

    @Bean
    ApplicationRunner init(TouristRepository touristRepository) {
        return database.addTouristToDatabase(touristRepository);
    }

    @Bean
    ApplicationRunner init1(FlightRepository flightRepository) {
        return database.addFlightToDatabase(flightRepository);
    }
}