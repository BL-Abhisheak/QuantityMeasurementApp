package com.quantity.model;
import java.io.Serializable;
import java.time.LocalDateTime;

public class QuantityMeasurementEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;
    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;
    private String operation;
    private String resultString;
    private double resultValue;
    private String resultUnit;
    private String resultMeasurementType;
    private String errorMessage;
    private boolean error;
    private LocalDateTime timestamp;

    public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType,
                                     String operation, String resultString) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.operation = operation;
        this.resultString = resultString;
        this.error = false;
        this.timestamp = LocalDateTime.now();
    }

    public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType,
                                     double thatValue, String thatUnit, String thatMeasurementType,
                                     String operation, double resultValue, String resultUnit, String resultMeasurementType) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
        this.error = false;
        this.timestamp = LocalDateTime.now();
    }

    public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType,
                                     double thatValue, String thatUnit, String thatMeasurementType,
                                     String operation, String errorMessage, boolean error) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public double getThisValue() { return thisValue; }
    public String getThisUnit() { return thisUnit; }
    public String getThisMeasurementType() { return thisMeasurementType; }
    public double getThatValue() { return thatValue; }
    public String getThatUnit() { return thatUnit; }
    public String getThatMeasurementType() { return thatMeasurementType; }
    public String getOperation() { return operation; }
    public String getResultString() { return resultString; }
    public double getResultValue() { return resultValue; }
    public String getResultUnit() { return resultUnit; }
    public String getResultMeasurementType() { return resultMeasurementType; }
    public String getErrorMessage() { return errorMessage; }
    public boolean isError() { return error; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        if (error) return "[ERROR] " + operation + ": " + errorMessage;
        if (resultString != null) return operation + "(" + thisValue + " " + thisUnit + ", " + thatValue + " " + thatUnit + ") = " + resultString;
        return operation + "(" + thisValue + " " + thisUnit + ", " + thatValue + " " + thatUnit + ") = " + resultValue + " " + resultUnit;
    }
}