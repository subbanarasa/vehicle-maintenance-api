package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.model.MaintenanceRecord;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.repository.VehicleMaintenanceRepository;
import com.interview.assignment.vehiclemaintenance.repository.VehicleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleMaintenanceServiceImplTest {

    @InjectMocks
    private VehicleMaintenanceServiceImpl vehicleMaintenanceService;

    @Mock
    VehicleMaintenanceRepository vehicleMaintenanceRepository;

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
    public void saveMaintenanceRecord() {
        MaintenanceRecord maintenanceRecordExpected = new MaintenanceRecord();
        maintenanceRecordExpected.setRecordId(1L);
        maintenanceRecordExpected.setVehicleId(1l);
        when(vehicleRepository.existsById(1l)).thenReturn(true);
        when(vehicleMaintenanceRepository.save(maintenanceRecordExpected)).thenReturn(maintenanceRecordExpected);
        MaintenanceRecord maintenanceRecord = vehicleMaintenanceService.saveMaintenanceRecord(maintenanceRecordExpected);
        assertEquals(maintenanceRecordExpected.getRecordId(), maintenanceRecord.getRecordId());
    }

    @Test
    public void getMaintenanceRecord() {
        MaintenanceRecord maintenanceRecordExpected = new MaintenanceRecord();
        maintenanceRecordExpected.setRecordId(1L);
        when(vehicleMaintenanceRepository.findById(maintenanceRecordExpected.getVehicleId())).thenReturn(Optional.of(maintenanceRecordExpected));
        Optional<MaintenanceRecord> optionalMaintenanceRecord = vehicleMaintenanceService.getMaintenanceRecord(maintenanceRecordExpected.getVehicleId());
        assertEquals(maintenanceRecordExpected.getRecordId(), optionalMaintenanceRecord.get().getRecordId());
    }

    @Test
    public void searchMaintenanceRecordsForVehicle() {
        MaintenanceRecord maintenanceRecordExpected = new MaintenanceRecord();
        maintenanceRecordExpected.setRecordId(1l);
        List<MaintenanceRecord> listExpected = new ArrayList<>();
        listExpected.add(maintenanceRecordExpected);
        when(vehicleMaintenanceRepository.findAllByVehicleId(1l)).thenReturn(listExpected);
        List<MaintenanceRecord> list = vehicleMaintenanceService.searchMaintenanceRecordsForVehicle(1l);
        assertEquals(listExpected, list);

    }

    @Test
    public void updateMaintenanceRecord() {
        MaintenanceRecord maintenanceRecordExpected = new MaintenanceRecord();
        maintenanceRecordExpected.setRecordId(1L);
        when(vehicleMaintenanceRepository.findById(1l)).thenReturn(Optional.of(maintenanceRecordExpected));
        when(vehicleMaintenanceRepository.save(maintenanceRecordExpected)).thenReturn(maintenanceRecordExpected);
        MaintenanceRecord vehicle = vehicleMaintenanceService.updateMaintenanceRecord(maintenanceRecordExpected.getRecordId(), maintenanceRecordExpected);
        assertEquals(maintenanceRecordExpected.getVehicleId(), vehicle.getVehicleId());

    }
}
