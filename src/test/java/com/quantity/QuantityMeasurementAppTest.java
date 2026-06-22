package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class QuantityMeasurementAppTest {

	@Test
	public void testAddition_ExplicitTargetUnit_Feet() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Inches() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(24.0, QuantityMeasurementApp.LengthUnit.INCH)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Yards() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(0.67, QuantityMeasurementApp.LengthUnit.YARDS)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.YARDS)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.FEET);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(9.0, QuantityMeasurementApp.LengthUnit.FEET)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Commutativity() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength r1 = a.add(b, QuantityMeasurementApp.LengthUnit.YARDS);
		QuantityMeasurementApp.QuantityLength r2 = b.add(a, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(r1.equals(r2));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_WithZero() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(1.67, QuantityMeasurementApp.LengthUnit.YARDS)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_NegativeValues() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCH)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		a.add(b, null);
	}

	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(1000.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(500.0, QuantityMeasurementApp.LengthUnit.FEET);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.INCH);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(18000.0, QuantityMeasurementApp.LengthUnit.INCH)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
		QuantityMeasurementApp.QuantityLength a = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
		QuantityMeasurementApp.QuantityLength result = a.add(b, QuantityMeasurementApp.LengthUnit.YARDS);
		assertTrue(result.equals(new QuantityMeasurementApp.QuantityLength(0.67, QuantityMeasurementApp.LengthUnit.YARDS)));
	}
}