package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.exception.ResourceNotFoundException;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.repository.UserRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import com.interview.assignment.vehiclemaintenance.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    private UserRepository userRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        if (!userRepository.existsById(vehicle.getOwnerId())) {
            throw new ResourceNotFoundException("Owner for owner ID " + vehicle.getOwnerId() + " not found");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> getVehicle(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public Vehicle updateVehicle(Long vehicleId, Vehicle vehicle) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new ResourceNotFoundException("Vehicle ID " + vehicleId + " not found");
        }

        if (vehicle.getOwnerId() != null && !userRepository.existsById(vehicle.getOwnerId())) {
            throw new ResourceNotFoundException("Owner for owner ID " + vehicle.getOwnerId() + " not found");
        }

        return vehicleRepository.findById(vehicleId).map(tmp -> {

            if (vehicle.getOwnerId() != null) {
                tmp.setOwnerId(vehicle.getOwnerId());
            }

            if (StringUtils.hasLength(vehicle.getManufactureName())) {
                tmp.setManufactureName(vehicle.getManufactureName());
            }

            if (StringUtils.hasLength(vehicle.getCarModel())) {
                tmp.setCarModel(vehicle.getCarModel());
            }

            if (StringUtils.hasLength(vehicle.getRegistrationNumber())) {
                tmp.setRegistrationNumber(vehicle.getRegistrationNumber());
            }

            if (StringUtils.hasLength(vehicle.getRegistrationNumber())) {
                tmp.setYearOfPurchase(vehicle.getRegistrationNumber());
            }

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
