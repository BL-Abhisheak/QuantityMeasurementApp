package com.quantity.service;

import com.quantity.Quantity;
import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.dto.QuantityMeasurementDTO;
import com.quantity.model.QuantityMeasurementEntity;
import com.quantity.repository.QuantityMeasurementRepository;
import com.quantity.unit.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public QuantityMeasurementDTO compareQuantities(QuantityInputDTO input) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        setupBaseEntityFields(entity, input, "compare");

        try {
            // Simulated calculation logic mapping back to core UC1-UC16 engines
            boolean evaluation = input.getThisQuantityDTO().getValue().equals(input.getThatQuantityDTO().getValue());
            entity.setResultString(String.valueOf(evaluation));
            entity.setError(false);
        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    @Override
    public QuantityMeasurementDTO convertQuantity(QuantityInputDTO input) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        setupBaseEntityFields(entity, input, "convert");

        try {
            // Simulated conversion computation sample
            double conversionResult = input.getThisQuantityDTO().getValue() * 12.0; // dummy conversion rule logic
            entity.setResultValue(conversionResult);
            entity.setError(false);
        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    @Override
    public QuantityMeasurementDTO addQuantities(QuantityInputDTO input) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        setupBaseEntityFields(entity, input, "add");

        if (!input.getThisQuantityDTO().getMeasurementType().equals(input.getThatQuantityDTO().getMeasurementType())) {
            entity.setError(true);
            entity.setErrorMessage("add Error: Cannot perform arithmetic between different measurement categories: "
                    + input.getThisQuantityDTO().getMeasurementType() + " and " + input.getThatQuantityDTO().getMeasurementType());
            return QuantityMeasurementDTO.fromEntity(repository.save(entity));
        }

        try {
            double additionResult = input.getThisQuantityDTO().getValue() + input.getThatQuantityDTO().getValue();
            entity.setResultValue(additionResult);
            entity.setResultUnit(input.getThisQuantityDTO().getUnit());
            entity.setResultMeasurementType(input.getThisQuantityDTO().getMeasurementType());
            entity.setError(false);
        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        return QuantityMeasurementDTO.fromEntity(repository.save(entity));
    }

    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByOperation(operation.toLowerCase()));
    }

    @Override
    public List<QuantityMeasurementDTO> getMeasurementTypeHistory(String measurementType) {
        return QuantityMeasurementDTO.fromEntityList(repository.findByThisMeasurementType(measurementType));
    }

    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        return QuantityMeasurementDTO.fromEntityList(repository.findByIsErrorTrue());
    }

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toUpperCase());
    }

    private void setupBaseEntityFields(QuantityMeasurementEntity entity, QuantityInputDTO input, String op) {
        entity.setThisValue(input.getThisQuantityDTO().getValue());
        entity.setThisUnit(input.getThisQuantityDTO().getUnit());
        entity.setThisMeasurementType(input.getThisQuantityDTO().getMeasurementType());
        entity.setThatValue(input.getThatQuantityDTO().getValue());
        entity.setThatUnit(input.getThatQuantityDTO().getUnit());
        entity.setThatMeasurementType(input.getThatQuantityDTO().getMeasurementType());
        entity.setOperation(op);
    }
}