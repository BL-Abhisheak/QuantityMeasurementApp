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
		Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
		Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
		demonstrateEquality(t1, t2);
		demonstrateConversion(t1, TemperatureUnit.FAHRENHEIT);
		demonstrateConversion(t2, TemperatureUnit.CELSIUS);

		try {
			t1.add(t2);
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught: " + e.getMessage());
		}

		try {
			t1.subtract(t2);
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught: " + e.getMessage());
		}

		try {
			t1.divide(t2);
		} catch (UnsupportedOperationException e) {
			System.out.println("Caught: " + e.getMessage());
		}
	}
}