package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;
import com.interview.assignment.vehiclemaintenance.service.VehicleOwnershipService;
import com.interview.assignment.vehiclemaintenance.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle/ownership")
public class VehicleOwnershipResource {

    private static Logger logger = LoggerFactory.getLogger(VehicleOwnershipResource.class);

    private VehicleOwnershipService vehicleOwnershipService;

    public VehicleOwnershipResource(VehicleOwnershipService vehicleOwnershipService) {
        this.vehicleOwnershipService = vehicleOwnershipService;
    }


    @PostMapping(value = "/add_vehicle")
    public ResponseEntity<StatusModel> addVehiclesToUser(@RequestBody VehicleOwnership vehicleOwnership) {
        logger.info("Add vehicle to user request:" + vehicleOwnership);
        VehicleOwnership saveVehicleOwnership = vehicleOwnershipService.saveVehicleOwnership(vehicleOwnership);
        if (saveVehicleOwnership == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, saveVehicleOwnership), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/change_ownership")
    public ResponseEntity<StatusModel> changeVehiclesOwnership(@RequestBody VehicleOwnership vehicleOwnership) {
        logger.info("change vehicle ownership: {}", vehicleOwnership);
        VehicleOwnership saveVehicleOwnership = vehicleOwnershipService.updateVehicleOwnership(vehicleOwnership);
        if (saveVehicleOwnership == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, saveVehicleOwnership), HttpStatus.OK);
        }

    }

    @GetMapping(value = "/get_vehicles/{ownerId}")
    public ResponseEntity<StatusModel> getVehiclesForOwner(@PathVariable long ownerId) {
        logger.info("Get vehicles for owner Id: {}", ownerId);
        List<Vehicle> vehicleList = vehicleOwnershipService.getVehiclesForOwner(ownerId);
        return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, vehicleList), HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete_ownership/{id}")
    public ResponseEntity<StatusModel> deleteVehicleOwnership(@PathVariable Long id) {
        logger.info("Delete vehicle ownership for ID: {}", id);
        vehicleOwnershipService.deleteVehicleOwnership(id);
        return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG), HttpStatus.OK);
    }

}
