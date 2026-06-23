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
			Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
			Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
			demonstrateEquality(v1, v2);
			demonstrateConversion(v1, VolumeUnit.MILLILITRE);
			demonstrateAddition(v1, v2, VolumeUnit.LITRE);

			Quantity<LengthUnit> la = new Quantity<>(1.0, LengthUnit.FEET);
			Quantity<VolumeUnit> va = new Quantity<>(1.0, VolumeUnit.LITRE);
			System.out.println(la.equals(va));
		}
}