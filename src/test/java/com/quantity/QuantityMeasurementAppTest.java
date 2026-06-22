package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-4;

	@Test
	public void testLengthUnitEnum_FeetConstant() {
		assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_InchesConstant() {
		assertEquals(1.0 / 12.0, LengthUnit.INCH.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_YardsConstant() {
		assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
	}

	@Test
	public void testLengthUnitEnum_CentimetersConstant() {
		assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_FeetToFeet() {
		assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_InchesToFeet() {
		assertEquals(1.0, LengthUnit.INCH.convertToBaseUnit(12.0), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_YardsToFeet() {
		assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testConvertToBaseUnit_CentimetersToFeet() {
		assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToFeet() {
		assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToInches() {
		assertEquals(12.0, LengthUnit.INCH.convertFromBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToYards() {
		assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToCentimeters() {
		assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
	}

	@Test
	public void testQuantityLengthRefactored_Equality() {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
		assertTrue(a.equals(b));
	}

	@Test
	public void testQuantityLengthRefactored_ConvertTo() {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength result = a.convertTo(LengthUnit.INCH);
		assertTrue(result.equals(new QuantityLength(12.0, LengthUnit.INCH)));
	}

	@Test
	public void testQuantityLengthRefactored_Add() {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
		QuantityLength result = a.add(b, LengthUnit.FEET);
		assertTrue(result.equals(new QuantityLength(2.0, LengthUnit.FEET)));
	}

	@Test
	public void testQuantityLengthRefactored_AddWithTargetUnit() {
		QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
		QuantityLength result = a.add(b, LengthUnit.YARDS);
		assertTrue(result.equals(new QuantityLength(0.67, LengthUnit.YARDS)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testQuantityLengthRefactored_NullUnit() {
		new QuantityLength(1.0, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testQuantityLengthRefactored_InvalidValue() {
		new QuantityLength(Double.NaN, LengthUnit.FEET);
	}

	@Test
	public void testRoundTripConversion_RefactoredDesign() {
		QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
		QuantityLength roundTrip = a.convertTo(LengthUnit.INCH).convertTo(LengthUnit.FEET);
		assertTrue(a.equals(roundTrip));
	}
}