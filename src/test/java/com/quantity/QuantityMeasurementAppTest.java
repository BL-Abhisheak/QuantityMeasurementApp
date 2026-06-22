package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class QuantityMeasurementAppTest {

	@Test
	public void testEquality_YardToYard_SameValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_YardToYard_DifferentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertFalse(a.equals(b));
	}

	@Test
	public void testEquality_YardToFeet_EquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_FeetToYard_EquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_YardToInches_EquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_InchesToYard_EquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_YardToFeet_NonEquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertFalse(a.equals(b));
	}

	@Test
	public void testEquality_CentimetersToCentimeters_SameValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_CentimetersToInches_EquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_CentimetersToFeet_NonEquivalentValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertFalse(a.equals(b));
	}

	@Test
	public void testEquality_MultiUnit_TransitiveProperty() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength c = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(a.equals(b));
		assertTrue(b.equals(c));
		assertTrue(a.equals(c));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEquality_YardWithNullUnit() {
		new QuantityMeasurementApp.QuantityLength(1.0, null);
	}

	@Test
	public void testEquality_YardSameReference() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(a.equals(a));
	}

	@Test
	public void testEquality_YardNullComparison() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertFalse(a.equals(null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEquality_CentimetersWithNullUnit() {
		new QuantityMeasurementApp.QuantityLength(2.0, null);
	}

	@Test
	public void testEquality_CentimetersSameReference() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		assertTrue(a.equals(a));
	}

	@Test
	public void testEquality_CentimetersNullComparison() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		assertFalse(a.equals(null));
	}

	@Test
	public void testEquality_AllUnits_ComplexScenario() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength c = new QuantityMeasurementApp.QuantityLength(72.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(a.equals(b));
		assertTrue(b.equals(c));
		assertTrue(a.equals(c));
	}
}