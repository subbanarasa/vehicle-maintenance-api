package com.interview.assignment.vehiclemaintenance.repository;

import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleOwnershipRepository extends CrudRepository<VehicleOwnership, Long> {

    List<VehicleOwnership> findAllByOwnerId(Long ownerId);

    VehicleOwnership findByVehicleId(Long vehicleId);

    VehicleOwnership findByOwnerIdAndVehicleId(Long ownerId, Long vehicleId);

}
