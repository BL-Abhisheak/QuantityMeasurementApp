package com.quantity.unit;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {
    static IMeasurable fromUnitName(String measurementType, String unit) {
        if (unit == null || measurementType == null) return null;
        String upperUnit = unit.toUpperCase();
        return switch (measurementType) {
            case "LengthUnit"      -> LengthUnit.valueOf(upperUnit);
            case "WeightUnit"      -> WeightUnit.valueOf(upperUnit);
            case "VolumeUnit"      -> VolumeUnit.valueOf(upperUnit);
            case "TemperatureUnit" -> TemperatureUnit.valueOf(upperUnit);
            default -> throw new IllegalArgumentException(
                    "Unknown measurement type: " + measurementType);
        };
    }

    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();

    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
    }

    Object getMeasurementType();
}
