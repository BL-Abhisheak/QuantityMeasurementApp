package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class QuantitymeasurementappApplicationTests {

	@Test
	public void testEquality_FeetToFeet_SameValue() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantitymeasurementappApplication.QuantityLength b = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_InchToInch_SameValue() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantitymeasurementappApplication.QuantityLength b = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_FeetToInch_EquivalentValue() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantitymeasurementappApplication.QuantityLength b = new QuantitymeasurementappApplication.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_InchToFeet_EquivalentValue() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantitymeasurementappApplication.QuantityLength b = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_FeetToFeet_DifferentValue() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantitymeasurementappApplication.QuantityLength b = new QuantitymeasurementappApplication.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertFalse(a.equals(b));
	}

	@Test
	public void testEquality_InchToInch_DifferentValue() {
		QuantitymeasurementappApplication.QuantityLength a = new QuanQuantitymeasurementappApplicationtityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantitymeasurementappApplication.QuantityLength b = new QuantitymeasurementappApplication.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertFalse(a.equals(b));
	}

	@Test()
	public void testEquality_InvalidUnit() {
		new QuantitymeasurementappApplication.QuantityLength(1.0, null);
	}

	@Test
	public void testEquality_NullComparison() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertFalse(a.equals(null));
	}

	@Test
	public void testEquality_SameReference() {
		QuantitymeasurementappApplication.QuantityLength a = new QuantitymeasurementappApplication.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(a.equals(a));
	}

	@Test
	public void testEquality_NullUnit() {
		new QuantitymeasurementappApplication.QuantityLength(1.0, null);
	}

}
