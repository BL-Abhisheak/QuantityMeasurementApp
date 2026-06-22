package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest

public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-2;

	@Test
	public void testAddition_SameUnit_FeetPlusFeet() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET)));
	}

	@Test
	public void testAddition_SameUnit_InchPlusInch() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH)));
	}

	@Test
	public void testAddition_CrossUnit_FeetPlusInches() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET)));
	}

	@Test
	public void testAddition_CrossUnit_InchPlusFeet() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(24.0, QuantityMeasurementApp.LengthUnit.INCH)));
	}

	@Test
	public void testAddition_CrossUnit_YardPlusFeet() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS)));
	}

	@Test
	public void testAddition_CrossUnit_CentimeterPlusInch() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(5.08, QuantityMeasurementApp.LengthUnit.CENTIMETERS)));
	}

	@Test
	public void testAddition_WithZero() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET)));
	}

	@Test
	public void testAddition_NegativeValues() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddition_NullSecondOperand() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		a.add(null);
	}

	@Test
	public void testAddition_LargeValues() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2e6, QuantityMeasurementApp.LengthUnit.FEET)));
	}
}