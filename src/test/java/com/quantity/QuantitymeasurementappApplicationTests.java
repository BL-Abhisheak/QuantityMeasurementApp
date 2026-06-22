package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class QuantitymeasurementappApplicationTests {

		@Test
		public void testFeetEquality_SameValue() {
			QuantitymeasurementappApplication.Feet a = new QuantitymeasurementappApplication.Feet(1.0);
			QuantitymeasurementappApplication.Feet b = new QuantitymeasurementappApplication.Feet(1.0);
			assertTrue(a.equals(b));
		}

		@Test
		public void testFeetEquality_DifferentValue() {
			QuantitymeasurementappApplication.Feet a = new QuantitymeasurementappApplication.Feet(1.0);
			QuantitymeasurementappApplication.Feet b = new QuantitymeasurementappApplication.Feet(2.0);
			assertFalse(a.equals(b));
		}

		@Test
		public void testFeetEquality_NullComparison() {
			QuantitymeasurementappApplication.Feet a = new QuantitymeasurementappApplication.Feet(1.0);
			assertFalse(a.equals(null));
		}

		@Test
		public void testFeetEquality_NonNumericInput() {
			QuantitymeasurementappApplication.Feet a = new QuantitymeasurementappApplication.Feet(1.0);
			assertFalse(a.equals("1.0"));
		}

		@Test
		public void testFeetEquality_SameReference() {
			QuantitymeasurementappApplication.Feet a = new QuantitymeasurementappApplication.Feet(1.0);
			assertTrue(a.equals(a));
		}

		@Test
		public void testInchesEquality_SameValue() {
			QuantitymeasurementappApplication.Inches a = new QuantitymeasurementappApplication.Inches(1.0);
			QuantitymeasurementappApplication.Inches b = new QuantitymeasurementappApplication.Inches(1.0);
			assertTrue(a.equals(b));
		}

		@Test
		public void testInchesEquality_DifferentValue() {
			QuantitymeasurementappApplication.Inches a = new QuantitymeasurementappApplication.Inches(1.0);
			QuantitymeasurementappApplication.Inches b = new QuantitymeasurementappApplication.Inches(2.0);
			assertFalse(a.equals(b));
		}

		@Test
		public void testInchesEquality_NullComparison() {
			QuantitymeasurementappApplication.Inches a = new QuantitymeasurementappApplication.Inches(1.0);
			assertFalse(a.equals(null));
		}

		@Test
		public void testInchesEquality_NonNumericInput() {
			QuantitymeasurementappApplication.Inches a = new QuantitymeasurementappApplication.Inches(1.0);
			assertFalse(a.equals("1.0"));
		}

		@Test
		public void testInchesEquality_SameReference() {
			QuantitymeasurementappApplication.Inches a = new QuantitymeasurementappApplication.Inches(1.0);
			assertTrue(a.equals(a));
		}
	}

}
