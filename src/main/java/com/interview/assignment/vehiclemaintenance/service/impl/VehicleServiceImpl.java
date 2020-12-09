package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.exception.ResourceNotFoundException;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import com.interview.assignment.vehiclemaintenance.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> getVehicle(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public Vehicle updateVehicle(Long vehicleId, Vehicle vehicle) {
        if (!vehicleRepository.existsById(vehicleId)) {
            new ResourceNotFoundException("Vehicle ID " + vehicleId + " not found");
        }
        return vehicleRepository.findById(vehicleId).map(tmp -> {
            tmp.setManufactureName(vehicle.getManufactureName());
            tmp.setCarModel(vehicle.getCarModel());
            tmp.setRegistrationNumber(vehicle.getRegistrationNumber());
            tmp.setYearOfPurchase(vehicle.getYearOfPurchase());
            return vehicleRepository.save(tmp);
        }).orElseThrow(() -> new ResourceNotFoundException("Vehicle ID " + vehicleId + " not found"));

    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isPresent()) {
            vehicleRepository.delete(optionalVehicle.get());
        } else {
            throw new ResourceNotFoundException("Vehicle ID " + vehicleId + " not found");
        }

    }
}
