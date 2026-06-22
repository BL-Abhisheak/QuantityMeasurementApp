package com.quantity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementApp {

	public static void main(String[] args) {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
		System.out.println(a.equals(b));
		System.out.println(a.convertTo(LengthUnit.INCH));
		System.out.println(a.add(b, LengthUnit.FEET));
		System.out.println(LengthUnit.FEET.convertToBaseUnit(12.0));
		System.out.println(LengthUnit.INCH.convertToBaseUnit(12.0));
	}
}