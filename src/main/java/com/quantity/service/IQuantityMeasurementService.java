package com.quantity.service;
import com.quantity.dto.QuantityDTO;

public interface IQuantityMeasurementService {
    boolean compare(QuantityDTO a, QuantityDTO b);
    QuantityDTO convert(QuantityDTO source, String targetUnit);
    QuantityDTO add(QuantityDTO a, QuantityDTO b);
    QuantityDTO add(QuantityDTO a, QuantityDTO b, String targetUnit);
    QuantityDTO subtract(QuantityDTO a, QuantityDTO b);
    QuantityDTO subtract(QuantityDTO a, QuantityDTO b, String targetUnit);
    double divide(QuantityDTO a, QuantityDTO b);
}
