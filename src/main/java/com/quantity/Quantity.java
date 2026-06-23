package com.quantity;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) throw new ArithmeticException("Divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

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

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Cannot perform arithmetic between different measurement categories: "
                    + this.unit.getClass().getSimpleName() + " and " + other.unit.getClass().getSimpleName());
        if (!Double.isFinite(other.value)) throw new IllegalArgumentException("Operand value must be finite");
        if (targetUnitRequired && targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        return operation.compute(this.toBaseUnit(), other.toBaseUnit());
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double converted = roundToTwoDecimals(targetUnit.convertFromBaseUnit(toBaseUnit()));
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(roundToTwoDecimals(unit.convertFromBaseUnit(baseResult)), this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(roundToTwoDecimals(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(roundToTwoDecimals(unit.convertFromBaseUnit(baseResult)), this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(roundToTwoDecimals(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
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