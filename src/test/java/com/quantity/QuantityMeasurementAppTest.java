package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuantityMeasurementAppTest {

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(a.equals(b));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(a.equals(b));
    }

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.convertTo(LengthUnit.INCH);
        assertTrue(result.equals(new Quantity<>(12.0, LengthUnit.INCH)));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = a.convertTo(WeightUnit.GRAM);
        assertTrue(result.equals(new Quantity<>(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        Quantity<LengthUnit> result = a.add(b, LengthUnit.FEET);
        assertTrue(result.equals(new Quantity<>(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = a.add(b, WeightUnit.KILOGRAM);
        assertTrue(result.equals(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> b = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(a.equals(b));
    }

    @Test
    public void testGenericQuantity_Conversion_AllUnitCombinations() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.YARDS);
        assertTrue(a.convertTo(LengthUnit.FEET).equals(new Quantity<>(3.0, LengthUnit.FEET)));
        assertTrue(a.convertTo(LengthUnit.INCH).equals(new Quantity<>(36.0, LengthUnit.INCH)));
    }

    @Test
    public void testGenericQuantity_Addition_AllUnitCombinations() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(500.0, WeightUnit.GRAM);
        assertTrue(a.add(b, WeightUnit.GRAM).equals(new Quantity<>(1500.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquals_GenericQuantity_ContractPreservation() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertFalse(a.equals(null));
    }

    @Test
    public void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        a.add(b);
        assertTrue(a.equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }
}