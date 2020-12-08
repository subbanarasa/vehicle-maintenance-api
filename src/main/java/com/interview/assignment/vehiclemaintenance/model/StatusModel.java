package com.interview.assignment.vehiclemaintenance.model;

import lombok.*;

@Data
@AllArgsConstructor
public class StatusModel {
    private int statusCode;
    private String statusMessage;
    private Object result;


}
