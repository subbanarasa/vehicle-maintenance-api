package com.interview.assignment.vehiclemaintenance.model;

import lombok.*;

@Data
@AllArgsConstructor
public class StatusModel {

    public StatusModel(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    private int statusCode;
    private String statusMessage;
    private Object result;


}
