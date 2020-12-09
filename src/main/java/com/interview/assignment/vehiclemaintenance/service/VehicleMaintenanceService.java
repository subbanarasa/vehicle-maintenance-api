package com.interview.assignment.vehiclemaintenance.service;

import com.interview.assignment.vehiclemaintenance.model.MaintenanceRecord;

import java.util.Optional;

public interface VehicleMaintenanceService {

    MaintenanceRecord saveMaintenanceRecord(MaintenanceRecord maintenanceRecord);

    Optional<MaintenanceRecord> getMaintenanceRecord(Long recordId);

    MaintenanceRecord updateMaintenanceRecord(Long recordId, MaintenanceRecord maintenanceRecord);

    void deleteMaintenanceRecord(Long recordId);

}
