package com.quantity;

import org.springframework.boot.SpringApplication;
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

	public static void main(String[] args) {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
		QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
		System.out.println(a.equals(b));

		QuantityLength c = new QuantityLength(1.0, LengthUnit.YARDS);
		QuantityLength d = new QuantityLength(36.0, LengthUnit.INCH);
		System.out.println(c.equals(d));

		QuantityLength e = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
		QuantityLength f = new QuantityLength(0.393701, LengthUnit.INCH);
		System.out.println(e.equals(f));
	}
}

}
