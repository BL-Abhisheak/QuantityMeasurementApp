package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-2;

	@Test
	public void testConversion_FeetToInches() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.INCH);
		assertEquals(12.0, result.convertTo(QuantityMeasurementApp.LengthUnit.INCH).convertTo(QuantityMeasurementApp.LengthUnit.FEET).convertTo(QuantityMeasurementApp.LengthUnit.INCH).toBaseUnit() * 12, EPSILON);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_InchesToFeet() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(24.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_YardsToInches() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_InchesToYards() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(72.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_CentimetersToInches() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_FeetToYard() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_ZeroValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(expected));
	}

	@Test
	public void testConversion_NegativeValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(-1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.convertTo(QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength expected = new QuantityMeasurementApp.QuantityLength(-12.0, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(expected));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConversion_InvalidUnit_Throws() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		a.convertTo(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConversion_NaNOrInfinite_Throws() {
		new QuantityMeasurementApp.QuantityLength(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET);
	}

	@Test
	public void testConversion_RoundTrip_PreservesValue() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength roundTrip = a.convertTo(QuantityMeasurementApp.LengthUnit.INCH).convertTo(QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(a.equals(roundTrip));
	}
}