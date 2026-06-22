package com.quantity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementApp {

	public enum LengthUnit {
		FEET(1.0),
		INCH(1.0 / 12.0),
		YARDS(3.0),
		CENTIMETERS(1.0 / 30.48);

		private final double conversionFactor;

		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}

		public double getConversionFactor() {
			return conversionFactor;
		}
	}

	public static class QuantityLength {
		private final double value;
		private final LengthUnit unit;

		public QuantityLength(double value, LengthUnit unit) {
			if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
			if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
			this.value = value;
			this.unit = unit;
		}

		private double toBaseUnit() {
			return value * unit.getConversionFactor();
		}

		public QuantityLength convertTo(LengthUnit targetUnit) {
			if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
			double converted = roundToTwoDecimals(toBaseUnit() / targetUnit.getConversionFactor());
			return new QuantityLength(converted, targetUnit);
		}

		public QuantityLength add(QuantityLength other) {
			if (other == null) throw new IllegalArgumentException("Operand cannot be null");
			double sumBase = this.toBaseUnit() + other.toBaseUnit();
			double result = roundToTwoDecimals(sumBase / this.unit.getConversionFactor());
			return new QuantityLength(result, this.unit);
		}

		public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
			if (other == null) throw new IllegalArgumentException("Operand cannot be null");
			if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
			double sumBase = this.toBaseUnit() + other.toBaseUnit();
			double result = roundToTwoDecimals(sumBase / targetUnit.getConversionFactor());
			return new QuantityLength(result, targetUnit);
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

	public static void demonstrateLengthEquality(QuantityLength a, QuantityLength b) {
		System.out.println(a + " == " + b + " : " + a.equals(b));
	}

	public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
		QuantityLength length = new QuantityLength(value, from);
		System.out.println("convert(" + value + " " + from + " to " + to + ") = " + length.convertTo(to));
	}

	public static void demonstrateLengthConversion(QuantityLength length, LengthUnit to) {
		System.out.println("convert(" + length + " to " + to + ") = " + length.convertTo(to));
	}

	public static void demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
		demonstrateLengthEquality(new QuantityLength(v1, u1), new QuantityLength(v2, u2));
	}

	public static void main(String[] args) {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
		System.out.println(a.add(b, LengthUnit.FEET));
		System.out.println(a.add(b, LengthUnit.INCH));
		System.out.println(a.add(b, LengthUnit.YARDS));
	}
}