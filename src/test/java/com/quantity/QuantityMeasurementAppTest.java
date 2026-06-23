package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-3;

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(b));
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertFalse(a.equals(b));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(a.equals(b));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        Quantity<VolumeUnit> a = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(b));
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> b = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(a.equals(b));
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> b = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(a.equals(b));
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(a.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(a));
    }

    @Test
    public void testEquality_ZeroValue() {
        Quantity<VolumeUnit> a = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertTrue(a.equals(b));
    }

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result.equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(3.79, VolumeUnit.LITRE)));
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(5.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity<VolumeUnit> a = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result.equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testConversion_NegativeValue() {
        Quantity<VolumeUnit> a = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result.equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertTrue(a.add(b).equals(new Quantity<>(3.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(a.add(b).equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(a.add(b, VolumeUnit.MILLILITRE).equals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testAddition_WithZero() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertTrue(a.add(b).equals(new Quantity<>(5.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testAddition_NegativeValues() {
        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        assertTrue(a.add(b).equals(new Quantity<>(3.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), 1e-6);
    }

    @Test
    public void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), 1e-6);
    }

    @Test
    public void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), 1e-4);
    }
}