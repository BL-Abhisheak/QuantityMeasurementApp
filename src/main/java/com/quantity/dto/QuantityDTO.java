package com.quantity.dto;

public class QuantityDTO {

    private double value;
    private String unit;
    private String type; // LENGTH, WEIGHT, VOLUME, TEMPERATURE

    public QuantityDTO(double value, String unit, String type) {
        this.value = value;
        this.unit = unit;
        this.type = type;
    }

    public double getValue() { return value; }
    public String getUnit() { return unit; }
    public String getType() { return type; }
}