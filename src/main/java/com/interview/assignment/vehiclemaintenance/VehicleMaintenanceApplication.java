package com.interview.assignment.vehiclemaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VehicleMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleMaintenanceApplication.class, args);
    }

}
