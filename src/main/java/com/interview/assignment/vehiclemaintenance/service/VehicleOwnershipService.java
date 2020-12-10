package com.interview.assignment.vehiclemaintenance.service;

import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;

import java.util.List;

public interface VehicleOwnershipService {

    VehicleOwnership saveVehicleOwnership(VehicleOwnership vehicleOwnership);

    List<Vehicle> getVehiclesForOwner(Long ownerId);

    VehicleOwnership updateVehicleOwnership(VehicleOwnership vehicleOwnership);

    void deleteVehicleOwnership(Long id);


}
