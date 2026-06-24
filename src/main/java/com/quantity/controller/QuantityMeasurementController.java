package com.quantity.controller;

import com.quantity.dto.QuantityDTO;
import com.quantity.service.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performComparison(QuantityDTO a, QuantityDTO b) {
        try {
            boolean result = service.compare(a, b);
            System.out.println("Compare " + a + " == " + b + " : " + result);
        } catch (Exception e) {
            System.out.println("Compare Error: " + e.getMessage());
        }
    }

    public void performConversion(QuantityDTO source, String targetUnit) {
        try {
            QuantityDTO result = service.convert(source, targetUnit);
            System.out.println("Convert " + source + " to " + targetUnit + " = " + result);
        } catch (Exception e) {
            System.out.println("Convert Error: " + e.getMessage());
        }
    }

    public void performAddition(QuantityDTO a, QuantityDTO b) {
        try {
            QuantityDTO result = service.add(a, b);
            System.out.println("Add " + a + " + " + b + " = " + result);
        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    public void performAddition(QuantityDTO a, QuantityDTO b, String targetUnit) {
        try {
            QuantityDTO result = service.add(a, b, targetUnit);
            System.out.println("Add " + a + " + " + b + " in " + targetUnit + " = " + result);
        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    public void performSubtraction(QuantityDTO a, QuantityDTO b) {
        try {
            QuantityDTO result = service.subtract(a, b);
            System.out.println("Subtract " + a + " - " + b + " = " + result);
        } catch (Exception e) {
            System.out.println("Subtract Error: " + e.getMessage());
        }
    }

    public void performDivision(QuantityDTO a, QuantityDTO b) {
        try {
            double result = service.divide(a, b);
            System.out.println("Divide " + a + " / " + b + " = " + result);
        } catch (Exception e) {
            System.out.println("Divide Error: " + e.getMessage());
        }
    }
}