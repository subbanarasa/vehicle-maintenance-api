package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.MaintenanceRecord;
import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.service.VehicleMaintenanceService;
import com.interview.assignment.vehiclemaintenance.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle/maintenance")
public class VehicleMaintenanceResource {
    private static Logger logger = LoggerFactory.getLogger(VehicleMaintenanceResource.class);

    private VehicleMaintenanceService vehicleMaintenanceService;

    public VehicleMaintenanceResource(VehicleMaintenanceService vehicleMaintenanceService) {
        this.vehicleMaintenanceService = vehicleMaintenanceService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<StatusModel> saveMaintenanceRecord(@RequestBody MaintenanceRecord maintenanceRecord) {
        if (logger.isDebugEnabled()) {
            logger.debug("Save Vehicle maintenance request:" + maintenanceRecord);
        }
        MaintenanceRecord saveMaintenanceRecord = vehicleMaintenanceService.saveMaintenanceRecord(maintenanceRecord);
        if (saveMaintenanceRecord == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, saveMaintenanceRecord), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateMaintenanceRecord(@PathVariable Long id, @RequestBody MaintenanceRecord maintenanceRecord) {
        logger.info("update Vehicle maintenance record for id:" + id + "|MaintenanceRecord" + maintenanceRecord);
        MaintenanceRecord updateMaintenanceRecord = vehicleMaintenanceService.updateMaintenanceRecord(id, maintenanceRecord);
        if (updateMaintenanceRecord == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, updateMaintenanceRecord), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getMaintenanceRecord(@PathVariable Long id) {
        logger.info("Get Vehicle Maintenance Record for id:" + id);
        Optional<MaintenanceRecord> optionalMaintenanceRecord = vehicleMaintenanceService.getMaintenanceRecord(id);
        if (optionalMaintenanceRecord.isPresent()) {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, optionalMaintenanceRecord.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/search/{vehicleId}")
    public ResponseEntity<?> searchMaintenanceRecordsForVehicle(@PathVariable Long vehicleId) {
        logger.info("Get Vehicle Maintenance Records for vehicleId:" + vehicleId);
        List<MaintenanceRecord> records = vehicleMaintenanceService.searchMaintenanceRecordsForVehicle(vehicleId);
        return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, records), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        logger.info("Delete vehicle maintenance record for id:" + id);
        vehicleMaintenanceService.deleteMaintenanceRecord(id);
        return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, null), HttpStatus.OK);
    }
}
