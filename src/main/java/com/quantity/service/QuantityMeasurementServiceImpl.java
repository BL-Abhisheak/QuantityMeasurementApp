package com.quantity.service;

import com.quantity.dto.QuantityDTO;
import com.quantity.exception.QuantityMeasurementException;
import com.quantity.model.QuantityMeasurementEntity;
import com.quantity.repository.IQuantityMeasurementRepository;
import com.quantity.unit.IMeasurable;

import java.util.function.DoubleBinaryOperator;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private IMeasurable resolveUnit(QuantityDTO dto) {
        return IMeasurable.fromUnitName(dto.getMeasurementType(), dto.getUnit());
    }

    private double toBase(QuantityDTO dto) {
        return resolveUnit(dto).convertToBaseUnit(dto.getValue());
    }

    @Override
    public boolean compare(QuantityDTO a, QuantityDTO b) {
        try {
            IMeasurable ua = resolveUnit(a);
            IMeasurable ub = resolveUnit(b);
            if (!ua.getMeasurementType().equals(ub.getMeasurementType()))
                throw new QuantityMeasurementException("Cannot compare different measurement categories");
            boolean result = Double.compare(ua.convertToBaseUnit(a.getValue()), ub.convertToBaseUnit(b.getValue())) == 0;
            repository.save(new QuantityMeasurementEntity(
                    a.getValue(), a.getUnit(), a.getMeasurementType(),
                    "compare", String.valueOf(result)));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(
                    a.getValue(), a.getUnit(), a.getMeasurementType(),
                    b.getValue(), b.getUnit(), b.getMeasurementType(),
                    "compare", e.getMessage(), true));
            throw new QuantityMeasurementException("compare Error: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO convert(QuantityDTO source, String targetUnit) {
        try {
            IMeasurable srcUnit = resolveUnit(source);
            IMeasurable tgt = IMeasurable.fromUnitName(source.getMeasurementType(), targetUnit);
            double baseValue = srcUnit.convertToBaseUnit(source.getValue());
            double converted = Math.round(tgt.convertFromBaseUnit(baseValue) * 100.0) / 100.0;
            QuantityDTO result = new QuantityDTO(converted, targetUnit, source.getMeasurementType());
            repository.save(new QuantityMeasurementEntity(
                    source.getValue(), source.getUnit(), source.getMeasurementType(),
                    "convert", converted + " " + targetUnit));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(
                    source.getValue(), source.getUnit(), source.getMeasurementType(),
                    0, targetUnit, source.getMeasurementType(),
                    "convert", e.getMessage(), true));
            throw new QuantityMeasurementException("convert Error: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO a, QuantityDTO b) {
        return performArithmetic(a, b, a.getUnit(), "add", (x, y) -> x + y);
    }

    @Override
    public QuantityDTO add(QuantityDTO a, QuantityDTO b, String targetUnit) {
        return performArithmetic(a, b, targetUnit, "add", (x, y) -> x + y);
    }

    @Override
    public QuantityDTO subtract(QuantityDTO a, QuantityDTO b) {
        return performArithmetic(a, b, a.getUnit(), "subtract", (x, y) -> x - y);
    }

    @Override
    public QuantityDTO subtract(QuantityDTO a, QuantityDTO b, String targetUnit) {
        return performArithmetic(a, b, targetUnit, "subtract", (x, y) -> x - y);
    }

    @Override
    public double divide(QuantityDTO a, QuantityDTO b) {
        try {
            IMeasurable ua = resolveUnit(a);
            IMeasurable ub = resolveUnit(b);
            if (!ua.getMeasurementType().equals(ub.getMeasurementType()))
                throw new QuantityMeasurementException("Cannot perform arithmetic between different measurement categories");
            ua.validateOperationSupport("divide");
            double baseA = ua.convertToBaseUnit(a.getValue());
            double baseB = ub.convertToBaseUnit(b.getValue());
            if (baseB == 0.0) throw new ArithmeticException("Divide by zero");
            double result = baseA / baseB;
            repository.save(new QuantityMeasurementEntity(
                    a.getValue(), a.getUnit(), a.getMeasurementType(),
                    b.getValue(), b.getUnit(), b.getMeasurementType(),
                    "divide", result, "", ""));
            return result;
        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(
                    a.getValue(), a.getUnit(), a.getMeasurementType(),
                    b.getValue(), b.getUnit(), b.getMeasurementType(),
                    "divide", e.getMessage(), true));
            throw new QuantityMeasurementException("divide Error: " + e.getMessage(), e);
        }
    }

    private QuantityDTO performArithmetic(QuantityDTO a, QuantityDTO b, String targetUnit,
                                          String operation, DoubleBinaryOperator op) {
        try {
            IMeasurable ua = resolveUnit(a);
            IMeasurable ub = resolveUnit(b);
            if (!ua.getMeasurementType().equals(ub.getMeasurementType()))
                throw new QuantityMeasurementException("Cannot perform arithmetic between different measurement categories: "
                        + ua.getMeasurementType() + " and " + ub.getMeasurementType());
            ua.validateOperationSupport(operation);
            IMeasurable tgt = IMeasurable.fromUnitName(a.getMeasurementType(), targetUnit);
            double baseResult = op.applyAsDouble(ua.convertToBaseUnit(a.getValue()), ub.convertToBaseUnit(b.getValue()));
            double converted = Math.round(tgt.convertFromBaseUnit(baseResult) * 100.0) / 100.0;
            QuantityDTO result = new QuantityDTO(converted, targetUnit, a.getMeasurementType());
            repository.save(new QuantityMeasurementEntity(
                    a.getValue(), a.getUnit(), a.getMeasurementType(),
                    b.getValue(), b.getUnit(), b.getMeasurementType(),
                    operation, converted, targetUnit, a.getMeasurementType()));
            return result;
        } catch (QuantityMeasurementException | UnsupportedOperationException e) {
            repository.save(new QuantityMeasurementEntity(
                    a.getValue(), a.getUnit(), a.getMeasurementType(),
                    b.getValue(), b.getUnit(), b.getMeasurementType(),
                    operation, e.getMessage(), true));
            throw new QuantityMeasurementException(operation + " Error: " + e.getMessage(), e);
        }
    }
}