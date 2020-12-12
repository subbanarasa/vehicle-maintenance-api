package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.service.UserService;
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

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleResourceTest {

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleResource vehicleResource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveVehicle() {
        when(vehicleService.saveVehicle(new Vehicle()))
                .thenReturn(new Vehicle());

        ResponseEntity<StatusModel> response = vehicleResource.saveVehicle(new Vehicle());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1l);
        when(vehicleService.updateVehicle(vehicle.getVehicleId(), vehicle))
                .thenReturn(vehicle);

        ResponseEntity<?> response = vehicleResource.updateVehicle(vehicle.getVehicleId(), vehicle);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1l);
        when(vehicleService.getVehicle(any()))
                .thenReturn(Optional.of(vehicle));

        ResponseEntity<?> response = vehicleResource.getVehicle(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void searchVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1l);
        vehicle.setRegistrationNumber("KA50N1542");

        when(vehicleService.searchVehicle(any()))
                .thenReturn(vehicle);

        ResponseEntity<?> response = vehicleResource.searchVehicle("KA50N1542");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}
