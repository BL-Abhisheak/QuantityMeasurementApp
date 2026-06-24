package com.quantity.model;

import com.quantity.dto.QuantityDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private QuantityDTO input1;
    private QuantityDTO input2;
    private Object result;
    private String error;

    public QuantityMeasurementEntity(
            String operation,
            QuantityDTO input1,
            QuantityDTO input2,
            Object result
    ) {
        this.operation = operation;
        this.input1 = input1;
        this.input2 = input2;
        this.result = result;
    }

    public QuantityMeasurementEntity(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public String toString() {
        return hasError()
                ? "Error: " + error
                : "Result: " + result;
    }
}