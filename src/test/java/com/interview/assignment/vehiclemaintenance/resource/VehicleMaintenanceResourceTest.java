package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.MaintenanceRecord;
import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.Vehicle;
import com.interview.assignment.vehiclemaintenance.service.VehicleMaintenanceService;
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
public class VehicleMaintenanceResourceTest {

    @Mock
    private VehicleMaintenanceService vehicleMaintenanceService;

    @InjectMocks
    private VehicleMaintenanceResource vehicleMaintenanceResource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void saveMaintenanceRecord() {
        when(vehicleMaintenanceService.saveMaintenanceRecord(new MaintenanceRecord()))
                .thenReturn(new MaintenanceRecord());

        ResponseEntity<StatusModel> response = vehicleMaintenanceResource.saveMaintenanceRecord(new MaintenanceRecord());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateMaintenanceRecord() {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setRecordId(1l);
        when(vehicleMaintenanceService.updateMaintenanceRecord(maintenanceRecord.getRecordId(), maintenanceRecord))
                .thenReturn(maintenanceRecord);

        ResponseEntity<?> response = vehicleMaintenanceResource.updateMaintenanceRecord(maintenanceRecord.getRecordId(), maintenanceRecord);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getMaintenanceRecord() {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setRecordId(1l);
        when(vehicleMaintenanceService.getMaintenanceRecord(any()))
                .thenReturn(Optional.of(maintenanceRecord));

        ResponseEntity<?> response = vehicleMaintenanceResource.getMaintenanceRecord(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void searchMaintenanceRecordsForVehicle() {
    }
}
