package com.quantity.service;
import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;


import java.util.List;

public interface IQuantityMeasurementService {

    QuantityMeasurementDTO compareQuantities(QuantityInputDTO input);

    QuantityMeasurementDTO convertQuantity(QuantityInputDTO input);

    QuantityMeasurementDTO addQuantities(QuantityInputDTO input);

    List<QuantityMeasurementDTO> getOperationHistory(String operation);

    List<QuantityMeasurementDTO> getMeasurementTypeHistory(String measurementType);

    List<QuantityMeasurementDTO> getErrorHistory();

    long getOperationCount(String operation);
}
