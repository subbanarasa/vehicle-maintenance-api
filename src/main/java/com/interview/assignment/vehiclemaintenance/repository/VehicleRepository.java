package com.interview.assignment.vehiclemaintenance.repository;

import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    Vehicle findByRegistrationNumber(String registrationNumber);

}
