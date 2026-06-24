package com.quantity;

import com.quantity.unit.WeightUnit;

public class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double converted = roundToTwoDecimals(targetUnit.convertFromBaseUnit(toBaseUnit()));
        return new QuantityWeight(converted, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new QuantityWeight(roundToTwoDecimals(unit.convertFromBaseUnit(sumBase)), this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new QuantityWeight(roundToTwoDecimals(targetUnit.convertFromBaseUnit(sumBase)), targetUnit);
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityWeight other = (QuantityWeight) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.name();
    }
}
