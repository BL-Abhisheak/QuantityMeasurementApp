package com.quantity.controller;

import com.quantity.dto.QuantityDTO;
import com.quantity.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(
            IQuantityMeasurementService service
    ) {
        this.service = service;
    }

    public void performComparison(
            QuantityDTO q1,
            QuantityDTO q2
    ) {
        System.out.println(service.compare(q1, q2));
    }

    public void performConversion(
            QuantityDTO q,
            String target
    ) {
        System.out.println(service.convert(q, target));
    }

    public void performAddition(
            QuantityDTO q1,
            QuantityDTO q2
    ) {
        System.out.println(service.add(q1, q2));
    }
}