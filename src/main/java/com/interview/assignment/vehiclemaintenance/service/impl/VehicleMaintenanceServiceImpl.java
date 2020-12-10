package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.exception.ResourceNotFoundException;
import com.interview.assignment.vehiclemaintenance.model.MaintenanceRecord;
import com.interview.assignment.vehiclemaintenance.repository.VehicleMaintenanceRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import com.interview.assignment.vehiclemaintenance.service.VehicleMaintenanceService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleMaintenanceServiceImpl implements VehicleMaintenanceService {

    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    private VehicleRepository vehicleRepository;

    public VehicleMaintenanceServiceImpl(VehicleMaintenanceRepository vehicleMaintenanceRepository, VehicleRepository vehicleRepository) {
        this.vehicleMaintenanceRepository = vehicleMaintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public MaintenanceRecord saveMaintenanceRecord(MaintenanceRecord maintenanceRecord) {
        if (!vehicleRepository.existsById(maintenanceRecord.getVehicleId())) {
            throw new ResourceNotFoundException("Vehicle for ID " + maintenanceRecord.getVehicleId() + " is not found");
        }
        return vehicleMaintenanceRepository.save(maintenanceRecord);
    }

    @Override
    public Optional<MaintenanceRecord> getMaintenanceRecord(Long recordId) {
        return vehicleMaintenanceRepository.findById(recordId);
    }

    @Override
    public List<MaintenanceRecord> searchMaintenanceRecordsForVehicle(Long vehicleId) {
        return vehicleMaintenanceRepository.findAllByVehicleId(vehicleId);
    }

    @Override
    public MaintenanceRecord updateMaintenanceRecord(Long recordId, MaintenanceRecord maintenanceRecord) {
        if (!vehicleMaintenanceRepository.existsById(recordId)) {
            new ResourceNotFoundException("Vehicle maintenance record ID " + recordId + " not found");
        }

        if (maintenanceRecord.getVehicleId() != null && !vehicleRepository.existsById(maintenanceRecord.getVehicleId())) {
            throw new ResourceNotFoundException("Vehicle for ID " + maintenanceRecord.getVehicleId() + " is not found");
        }

        return vehicleMaintenanceRepository.findById(recordId).map(tmp -> {

            if (maintenanceRecord.getVehicleId() != null) {
                tmp.setVehicleId(maintenanceRecord.getVehicleId());
            }

            if (maintenanceRecord.getMaintenanceCost() != null) {
                tmp.setMaintenanceCost(maintenanceRecord.getMaintenanceCost());
            }

            if (maintenanceRecord.getMaintenanceDate() != null) {
                tmp.setMaintenanceDate(maintenanceRecord.getMaintenanceDate());
            }

            if (StringUtils.hasLength(maintenanceRecord.getServiceManager())) {
                tmp.setServiceManager(maintenanceRecord.getServiceManager());
            }

            if (StringUtils.hasLength(maintenanceRecord.getServiceStationName())) {
                tmp.setServiceStationName(maintenanceRecord.getServiceStationName());
            }

            if (StringUtils.hasLength(maintenanceRecord.getComments())) {
                tmp.setComments(maintenanceRecord.getComments());
            }
            return vehicleMaintenanceRepository.save(tmp);
        }).orElseThrow(() -> new ResourceNotFoundException("Vehicle maintenance ID " + recordId + " not found"));
    }

    @Override
    public void deleteMaintenanceRecord(Long recordId) {
        Optional<MaintenanceRecord> optionalVehicle = vehicleMaintenanceRepository.findById(recordId);
        if (optionalVehicle.isPresent()) {
            vehicleMaintenanceRepository.delete(optionalVehicle.get());
        } else {
            throw new ResourceNotFoundException("Record ID " + recordId + " not found");
        }

    }
}
