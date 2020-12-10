package com.interview.assignment.vehiclemaintenance.service;

import com.interview.assignment.vehiclemaintenance.model.Vehicle;

import java.util.Optional;

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);

    Optional<Vehicle> getVehicle(Long vehicleId);

    Vehicle searchVehicle(String registrationNumber);

    Vehicle updateVehicle(Long vehicleId, Vehicle vehicle);

    void deleteVehicle(Long vehicleId);
}
