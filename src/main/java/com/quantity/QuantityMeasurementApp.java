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

	public static void main(String[] args) {
		Quantity<LengthUnit> la = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> lb = new Quantity<>(12.0, LengthUnit.INCH);
		demonstrateEquality(la, lb);
		demonstrateConversion(la, LengthUnit.INCH);
		demonstrateAddition(la, lb, LengthUnit.FEET);

		Quantity<WeightUnit> wa = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> wb = new Quantity<>(1000.0, WeightUnit.GRAM);
		demonstrateEquality(wa, wb);
		demonstrateConversion(wa, WeightUnit.GRAM);
		demonstrateAddition(wa, wb, WeightUnit.KILOGRAM);

		Quantity<LengthUnit> lc = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<WeightUnit> wc = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		System.out.println(lc.equals(wc));
	}
}