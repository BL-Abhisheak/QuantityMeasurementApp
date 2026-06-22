package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-3;

	@Test
	public void testEquality_KilogramToKilogram_SameValue() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
		assertFalse(a.equals(b));
	}

	@Test
	public void testEquality_KilogramToGram_EquivalentValue() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		QuantityWeight a = new QuantityWeight(1000.0, WeightUnit.GRAM);
		QuantityWeight b = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		assertTrue(a.equals(b));
	}

	@Test
	public void testEquality_WeightVsLength_Incompatible() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityLength b = new QuantityLength(1.0, LengthUnit.FEET);
		assertFalse(a.equals(b));
	}

	@Test
	public void testEquality_NullComparison() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		assertFalse(a.equals(null));
	}

	@Test
	public void testEquality_SameReference() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		assertTrue(a.equals(a));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEquality_NullUnit() {
		new QuantityWeight(1.0, null);
	}

	@Test
	public void testEquality_ZeroValue() {
		QuantityWeight a = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(0.0, WeightUnit.GRAM);
		assertTrue(a.equals(b));
	}

	@Test
	public void testConversion_PoundToKilogram() {
		QuantityWeight a = new QuantityWeight(2.20462, WeightUnit.POUND);
		QuantityWeight result = a.convertTo(WeightUnit.KILOGRAM);
		assertTrue(result.equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testConversion_KilogramToPound() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight result = a.convertTo(WeightUnit.POUND);
		assertEquals(2.20, result.convertTo(WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM).toBaseUnit(), EPSILON);
	}

	@Test
	public void testConversion_SameUnit() {
		QuantityWeight a = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight result = a.convertTo(WeightUnit.KILOGRAM);
		assertTrue(result.equals(new QuantityWeight(5.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testConversion_ZeroValue() {
		QuantityWeight a = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
		QuantityWeight result = a.convertTo(WeightUnit.GRAM);
		assertTrue(result.equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
	}

	@Test
	public void testConversion_NegativeValue() {
		QuantityWeight a = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
		QuantityWeight result = a.convertTo(WeightUnit.GRAM);
		assertTrue(result.equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testAddition_SameUnit_KilogramPlusKilogram() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
		assertTrue(a.add(b).equals(new QuantityWeight(3.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_CrossUnit_KilogramPlusGram() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
		assertTrue(a.add(b).equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_ExplicitTargetUnit_Kilogram() {
		QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
		assertTrue(a.add(b, WeightUnit.GRAM).equals(new QuantityWeight(2000.0, WeightUnit.GRAM)));
	}

	@Test
	public void testAddition_WithZero() {
		QuantityWeight a = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(0.0, WeightUnit.GRAM);
		assertTrue(a.add(b).equals(new QuantityWeight(5.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_NegativeValues() {
		QuantityWeight a = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(-2000.0, WeightUnit.GRAM);
		assertTrue(a.add(b).equals(new QuantityWeight(3.0, WeightUnit.KILOGRAM)));
	}

	@Test
	public void testAddition_LargeValues() {
		QuantityWeight a = new QuantityWeight(1e6, WeightUnit.KILOGRAM);
		QuantityWeight b = new QuantityWeight(1e6, WeightUnit.KILOGRAM);
		assertTrue(a.add(b).equals(new QuantityWeight(2e6, WeightUnit.KILOGRAM)));
	}
}