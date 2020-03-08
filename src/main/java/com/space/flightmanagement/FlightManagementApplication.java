package com.space.flightmanagement;

import com.space.flightmanagement.data.ExampleData;
import com.space.flightmanagement.repository.TouristRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlightManagementApplication {

    private final ExampleData exampleData = new ExampleData();

    public static void main(String[] args) {
        SpringApplication.run(FlightManagementApplication.class, args);
    }

    @Bean
    ApplicationRunner init(TouristRepository touristRepository) {
        return exampleData.addTouristToDatabase(touristRepository);
    }
}