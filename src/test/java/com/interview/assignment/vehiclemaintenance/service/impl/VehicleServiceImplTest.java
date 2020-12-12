package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    VehicleRepository vehicleRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveVehicle() {
        Vehicle vehicleExpected = new Vehicle();
        vehicleExpected.setRegistrationNumber("KA50N1542");
        when(vehicleRepository.findByRegistrationNumber(vehicleExpected.getRegistrationNumber())).thenReturn(null);
        when(vehicleRepository.save(vehicleExpected)).thenReturn(vehicleExpected);
        Vehicle vehicle = vehicleService.saveVehicle(vehicleExpected);
        assertEquals(vehicleExpected.getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    @Test
    public void getVehicle() {
        Vehicle vehicleExpected = new Vehicle();
        vehicleExpected.setVehicleId(1l);
        when(vehicleRepository.findById(vehicleExpected.getVehicleId())).thenReturn(Optional.of(vehicleExpected));
        Optional<Vehicle> optionalUser = vehicleService.getVehicle(vehicleExpected.getVehicleId());
        assertEquals(vehicleExpected.getVehicleId(), optionalUser.get().getVehicleId());
    }

    @Test
    public void searchVehicle() {
        Vehicle vehicleExpected = new Vehicle();
        vehicleExpected.setRegistrationNumber("KA50N1542");
        when(vehicleRepository.findByRegistrationNumber(vehicleExpected.getRegistrationNumber())).thenReturn(vehicleExpected);
        Vehicle vehicle = vehicleService.searchVehicle(vehicleExpected.getRegistrationNumber());
        assertEquals(vehicleExpected.getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    @Test
    public void updateVehicle() {
        Vehicle vehicleExpected = new Vehicle();
        vehicleExpected.setVehicleId(1l);
        vehicleExpected.setRegistrationNumber("KA50N1542");

        when(vehicleRepository.existsById(1l)).thenReturn(true);
        when(vehicleRepository.findById(1l)).thenReturn(Optional.of(vehicleExpected));
        when(vehicleRepository.save(vehicleExpected)).thenReturn(vehicleExpected);
        Vehicle vehicle = vehicleService.updateVehicle(vehicleExpected.getVehicleId(), vehicleExpected);
        assertEquals(vehicleExpected.getVehicleId(), vehicle.getVehicleId());
    }


}
