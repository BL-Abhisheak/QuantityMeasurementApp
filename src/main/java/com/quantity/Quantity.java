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
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new Quantity<>(roundToTwoDecimals(unit.convertFromBaseUnit(sumBase)), this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories");
        double sumBase = this.toBaseUnit() + other.toBaseUnit();
        return new Quantity<>(roundToTwoDecimals(targetUnit.convertFromBaseUnit(sumBase)), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories");
        double diffBase = this.toBaseUnit() - other.toBaseUnit();
        return new Quantity<>(roundToTwoDecimals(unit.convertFromBaseUnit(diffBase)), this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories");
        double diffBase = this.toBaseUnit() - other.toBaseUnit();
        return new Quantity<>(roundToTwoDecimals(targetUnit.convertFromBaseUnit(diffBase)), targetUnit);
    }

    public double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (this.unit.getClass() != other.unit.getClass()) throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories");
        double otherBase = other.toBaseUnit();
        if (otherBase == 0.0) throw new ArithmeticException("Divide by zero");
        return this.toBaseUnit() / otherBase;
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