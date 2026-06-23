package com.quantity;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    public U getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double converted = roundToTwoDecimals(targetUnit.convertFromBaseUnit(toBaseUnit()));
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot add different measurement categories");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new Quantity<>(roundToTwoDecimals(unit.convertFromBaseUnit(sumBase)), this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot add different measurement categories");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new Quantity<>(roundToTwoDecimals(targetUnit.convertFromBaseUnit(sumBase)), targetUnit);
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (this.unit.getClass() != other.unit.getClass()) return false;
        return Double.compare(this.toBaseUnit(), other.unit.convertToBaseUnit(other.value)) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}