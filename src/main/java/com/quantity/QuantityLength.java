package com.quantity;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double converted = roundToTwoDecimals(targetUnit.convertFromBaseUnit(toBaseUnit()));
        return new QuantityLength(converted, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new QuantityLength(roundToTwoDecimals(unit.convertFromBaseUnit(sumBase)), this.unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new QuantityLength(roundToTwoDecimals(targetUnit.convertFromBaseUnit(sumBase)), targetUnit);
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
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
