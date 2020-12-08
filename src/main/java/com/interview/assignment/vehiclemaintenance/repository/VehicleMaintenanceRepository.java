package com.interview.assignment.vehiclemaintenance.repository;

import com.interview.assignment.vehiclemaintenance.model.VehicleMaintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMaintenanceRepository extends CrudRepository<VehicleMaintenance, Long> {
}
