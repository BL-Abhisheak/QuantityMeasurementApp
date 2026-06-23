package com.quantity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementApp {

	public static <U extends IMeasurable> void demonstrateEquality(Quantity<U> a, Quantity<U> b) {
		System.out.println(a + " == " + b + " : " + a.equals(b));
	}

	public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> a, U targetUnit) {
		System.out.println("convert(" + a + " to " + targetUnit.getUnitName() + ") = " + a.convertTo(targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> a, Quantity<U> b, U targetUnit) {
		System.out.println("add(" + a + ", " + b + ", " + targetUnit.getUnitName() + ") = " + a.add(b, targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> a, Quantity<U> b, U targetUnit) {
		System.out.println("subtract(" + a + ", " + b + ", " + targetUnit.getUnitName() + ") = " + a.subtract(b, targetUnit));
	}

	public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> a, Quantity<U> b) {
		System.out.println("divide(" + a + ", " + b + ") = " + a.divide(b));
	}

	public static void main(String[] args) {
		Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
		Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
		demonstrateSubtraction(a, b, LengthUnit.FEET);
		demonstrateDivision(a, new Quantity<>(2.0, LengthUnit.FEET));

		Quantity<WeightUnit> wa = new Quantity<>(10.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> wb = new Quantity<>(5000.0, WeightUnit.GRAM);
		demonstrateSubtraction(wa, wb, WeightUnit.KILOGRAM);
	}
}