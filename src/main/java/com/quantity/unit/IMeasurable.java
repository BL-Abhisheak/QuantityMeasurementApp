package com.quantity.unit;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    // Default: supports arithmetic
    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // Default validation (no restriction)
    default void validateOperationSupport(String operation) {
        // No restriction for most units
    }
}

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}