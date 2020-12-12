package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.model.VehicleOwnership;
import com.interview.assignment.vehiclemaintenance.repository.UserRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleOwnershipRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleOwnershipServiceImplTest {

    @InjectMocks
    private VehicleOwnershipServiceImpl vehicleOwnershipService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VehicleOwnershipRepository vehicleOwnershipRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveVehicleOwnership() {
        VehicleOwnership vehicleOwnershipExpected = new VehicleOwnership();
        vehicleOwnershipExpected.setVehicleId(1l);
        vehicleOwnershipExpected.setOwnerId(1l);
        when(vehicleRepository.existsById(1l)).thenReturn(true);
        when(userRepository.existsById(1l)).thenReturn(true);
        when(vehicleOwnershipRepository.save(vehicleOwnershipExpected)).thenReturn(vehicleOwnershipExpected);

        VehicleOwnership vehicleOwnership = vehicleOwnershipService.saveVehicleOwnership(vehicleOwnershipExpected);
        assertEquals(vehicleOwnershipExpected.getOwnerId(), vehicleOwnership.getOwnerId());
        assertEquals(vehicleOwnershipExpected.getVehicleId(), vehicleOwnership.getVehicleId());
    }

    @Test
    public void getVehiclesForOwner() {
        VehicleOwnership vehicleOwnershipExpected = new VehicleOwnership();
        vehicleOwnershipExpected.setVehicleId(1l);
        vehicleOwnershipExpected.setOwnerId(1l);

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(1l);
        List<Vehicle> listExpected = Arrays.asList(vehicle);

        when(userRepository.existsById(1l)).thenReturn(true);
        when(vehicleRepository.findAllById(Arrays.asList(1l))).thenReturn(listExpected);
        when(vehicleOwnershipRepository.findAllByOwnerId(1l)).thenReturn(Arrays.asList(vehicleOwnershipExpected));
        List<Vehicle> list = vehicleOwnershipService.getVehiclesForOwner(1l);
        assertEquals(listExpected.size(), list.size());
    }

    @Test
    public void updateVehicleOwnership() {
        VehicleOwnership vehicleOwnershipExpected = new VehicleOwnership();
        vehicleOwnershipExpected.setVehicleId(1l);
        vehicleOwnershipExpected.setOwnerId(1l);
        when(vehicleRepository.existsById(1l)).thenReturn(true);
        when(userRepository.existsById(1l)).thenReturn(true);
        when(vehicleOwnershipRepository.findByVehicleId(1l)).thenReturn(vehicleOwnershipExpected);
        when(vehicleOwnershipRepository.save(vehicleOwnershipExpected)).thenReturn(vehicleOwnershipExpected);

        VehicleOwnership vehicleOwnership = vehicleOwnershipService.updateVehicleOwnership(vehicleOwnershipExpected);
        assertEquals(vehicleOwnershipExpected.getOwnerId(), vehicleOwnership.getOwnerId());
        assertEquals(vehicleOwnershipExpected.getVehicleId(), vehicleOwnership.getVehicleId());
    }
}
