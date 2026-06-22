package com.quantity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class QuantityMeasurementApp {

	public static void main(String[] args) {
		QuantityLength la = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength lb = new QuantityLength(12.0, LengthUnit.INCH);
		System.out.println(la.equals(lb));

		QuantityWeight wa = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight wb = new QuantityWeight(1000.0, WeightUnit.GRAM);
		System.out.println(wa.equals(wb));

		QuantityWeight wc = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		System.out.println(wc.convertTo(WeightUnit.GRAM));

		QuantityWeight wd = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight we = new QuantityWeight(1000.0, WeightUnit.GRAM);
		System.out.println(wd.add(we, WeightUnit.GRAM));
	}
}