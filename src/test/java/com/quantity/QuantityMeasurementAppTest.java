package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantityMeasurementAppTest {

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        assertTrue(a.subtract(b).equals(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(3.0, VolumeUnit.LITRE);
        assertTrue(a.subtract(b).equals(new Quantity<>(7.0, VolumeUnit.LITRE)));
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
        assertTrue(a.subtract(b).equals(new Quantity<>(9.5, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
        assertTrue(a.subtract(b, LengthUnit.FEET).equals(new Quantity<>(9.5, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
        assertTrue(a.subtract(b, LengthUnit.INCH).equals(new Quantity<>(114.0, LengthUnit.INCH)));
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);
        assertTrue(a.subtract(b).equals(new Quantity<>(-5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(120.0, LengthUnit.INCH);
        assertTrue(a.subtract(b).equals(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.INCH);
        assertTrue(a.subtract(b).equals(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        assertFalse(a.subtract(b).equals(b.subtract(a)));
    }

    @Test
    void testSubtraction_NullOperand() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> a.subtract(null)
        );
    }

    @Test
    void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> a.subtract(b, null)
        );
    }

    @Test
    @SuppressWarnings({"rawtypes", "unchecked"})
    void testSubtraction_CrossCategory() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(
                IllegalArgumentException.class,
                () -> a.subtract((Quantity) b)
        );
    }

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, a.divide(b), 1e-6);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        Quantity<LengthUnit> a = new Quantity<>(24.0, LengthUnit.INCH);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(1.0, a.divide(b), 1e-6);
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(
                ArithmeticException.class,
                () -> a.divide(b)
        );
    }

    @Test
    void testDivision_NullOperand() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> a.divide(null)
        );
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        a.subtract(b);
        assertTrue(a.equals(new Quantity<>(10.0, LengthUnit.FEET)));
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        a.divide(b);
        assertTrue(a.equals(new Quantity<>(10.0, LengthUnit.FEET)));
    }

    @Test
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(3.79, VolumeUnit.LITRE)));
    }

    @Test
    void testConversion_SameUnit() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(5.0, VolumeUnit.LITRE)));
    }

    @Test
    void testConversion_ZeroValue() {
        Quantity<VolumeUnit> a = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result.equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testConversion_NegativeValue() {
        Quantity<VolumeUnit> a = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result.equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertTrue(a.add(b).equals(new Quantity<>(3.0, VolumeUnit.LITRE)));
    }


    @Test
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), 1e-6);
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), 1e-6);
    }

    @Test
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), 1e-4);
    }
}