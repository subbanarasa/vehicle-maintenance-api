package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;
import com.interview.assignment.vehiclemaintenance.service.VehicleOwnershipService;
import com.interview.assignment.vehiclemaintenance.service.VehicleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleOwnershipResourceTest {

    @Mock
    private VehicleOwnershipService vehicleOwnershipService;

    @InjectMocks
    private VehicleOwnershipResource vehicleOwnershipResource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addVehiclesToUser() {
        when(vehicleOwnershipService.saveVehicleOwnership(new VehicleOwnership()))
                .thenReturn(new VehicleOwnership());

        ResponseEntity<StatusModel> response = vehicleOwnershipResource.addVehiclesToUser(new VehicleOwnership());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void changeVehiclesOwnership() {
        VehicleOwnership vehicleOwnership = new VehicleOwnership();
        vehicleOwnership.setOwnerId(1l);
        when(vehicleOwnershipService.updateVehicleOwnership(vehicleOwnership))
                .thenReturn(vehicleOwnership);

        ResponseEntity<?> response = vehicleOwnershipResource.changeVehiclesOwnership(vehicleOwnership);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getVehiclesForOwner() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1l);
        when(vehicleOwnershipService.getVehiclesForOwner(1l))
                .thenReturn(Arrays.asList(vehicle));

        ResponseEntity<?> response = vehicleOwnershipResource.getVehiclesForOwner(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
