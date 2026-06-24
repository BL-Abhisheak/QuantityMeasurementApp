package com.quantity.service;
import com.quantity.dto.QuantityDTO;
import com.quantity.model.QuantityMeasurementEntity;


public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(
            QuantityDTO q1,
            QuantityDTO q2
    );

    QuantityMeasurementEntity convert(
            QuantityDTO q,
            String targetUnit
    );

    QuantityMeasurementEntity add(
            QuantityDTO q1,
            QuantityDTO q2
    );
}
