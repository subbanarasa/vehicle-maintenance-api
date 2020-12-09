package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.service.VehicleService;
import com.interview.assignment.vehiclemaintenance.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class VehicleResource {

    private static Logger logger = LoggerFactory.getLogger(VehicleResource.class);

    private VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<StatusModel> saveVehicle(@RequestBody Vehicle vehicle) {
        if (logger.isDebugEnabled()) {
            logger.debug("Save Vehicle request:" + vehicle);
        }
        Vehicle vehicleSaved = vehicleService.saveVehicle(vehicle);
        if (vehicleSaved == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, vehicleSaved), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        if (logger.isDebugEnabled()) {
            logger.debug("update Vehicle for id:" + id + "|vehicle" + vehicle);
        }
        Vehicle vehicleSaved = vehicleService.updateVehicle(id, vehicle);
        if (vehicleSaved == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, vehicleSaved), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get Vehicle for id:" + id);
        }
        Optional<Vehicle> optionalVehicle = vehicleService.getVehicle(id);
        if (optionalVehicle.isEmpty()) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, optionalVehicle.get()), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Delete vehicle for id:" + id);
        }
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, null), HttpStatus.OK);
    }

}


