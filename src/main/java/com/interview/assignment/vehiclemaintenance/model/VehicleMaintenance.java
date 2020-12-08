package com.interview.assignment.vehiclemaintenance.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class VehicleMaintenance {

    @Id
    private Long recordId;
}
