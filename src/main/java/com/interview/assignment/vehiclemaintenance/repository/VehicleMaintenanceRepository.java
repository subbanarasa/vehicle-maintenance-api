package com.interview.assignment.vehiclemaintenance.repository;

import com.interview.assignment.vehiclemaintenance.model.MaintenanceRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMaintenanceRepository extends CrudRepository<MaintenanceRecord, Long> {

    List<MaintenanceRecord> findAllByVehicleId(Long vehicleId);
}
