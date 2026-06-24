package com.quantity;

import com.quantity.unit.LengthUnit;


public class QuantityLength {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        validateValue(value);

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    private void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public QuantityLength convertTo(
            LengthUnit targetUnit
    ) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(
                convertedValue,
                targetUnit
        );
    }

    public QuantityLength add(
            QuantityLength other
    ) {

        return add(other, this.unit);
    }

    public QuantityLength add(
            QuantityLength other,
            LengthUnit targetUnit
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Second operand cannot be null"
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double firstBase = unit.convertToBaseUnit(value);

        double secondBase = other.unit.convertToBaseUnit(
                        other.value
                );

        double totalBase = firstBase + secondBase;

        double result = targetUnit.convertFromBaseUnit(
                        totalBase
                );

        return new QuantityLength(
                result,
                targetUnit
        );
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null ||
                getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength other =
                (QuantityLength) obj;

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(
                        other.value
                );

        return Math.abs(
                thisBase - otherBase
        ) < EPSILON;
    }

    @Override
    public String toString() {

        return "Quantity(" +
                value +
                ", " +
                unit +
                ")";
    }
}