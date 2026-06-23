package com.quantity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
public class QuantityMeasurementAppTest {

    @Test
    public void testArithmeticOperation_Add_EnumComputation() {
        assertEquals(15.0, Quantity.ArithmeticOperation.ADD.compute(10, 5), 1e-6);
    }

    @Test
    public void testArithmeticOperation_Subtract_EnumComputation() {
        assertEquals(5.0, Quantity.ArithmeticOperation.SUBTRACT.compute(10, 5), 1e-6);
    }

    @Test
    public void testArithmeticOperation_Divide_EnumComputation() {
        assertEquals(2.0, Quantity.ArithmeticOperation.DIVIDE.compute(10, 5), 1e-6);
    }

    @Test
    public void testAdd_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(a.add(b, LengthUnit.FEET).equals(new Quantity<>(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testSubtract_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCH);
        assertTrue(a.subtract(b).equals(new Quantity<>(9.5, LengthUnit.FEET)));
    }

    @Test
    public void testDivide_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, a.divide(b), 1e-6);
    }

    @Test
    public void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        try { a.add(null); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
        try { a.subtract(null); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
        try { a.divide(null); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
    }

    @Test
    public void testValidation_CrossCategory_ConsistentAcrossOperations() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> b = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        try { a.add((Quantity) b); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
        try { a.subtract((Quantity) b); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
        try { a.divide((Quantity) b); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
    }

    @Test
    public void testValidation_NullTargetUnit_AddSubtractReject() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(1.0, LengthUnit.FEET);
        try { a.add(b, null); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
        try { a.subtract(b, null); fail(); } catch (IllegalArgumentException e) { assertNotNull(e.getMessage()); }
    }

    @Test
    public void testRounding_AddSubtract_TwoDecimalPlaces() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        Quantity<LengthUnit> result = a.add(b, LengthUnit.YARDS);
        assertEquals(0.67, result.getValue(), 1e-6);
    }

    @Test
    public void testImplicitTargetUnit_AddSubtract() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(LengthUnit.FEET, a.add(b).getUnit());
        assertEquals(LengthUnit.FEET, a.subtract(b).getUnit());
    }

    @Test
    public void testAllOperations_AcrossAllCategories() {
        Quantity<WeightUnit> wa = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> wb = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertTrue(wa.add(wb).equals(new Quantity<>(15.0, WeightUnit.KILOGRAM)));
        assertTrue(wa.subtract(wb).equals(new Quantity<>(5.0, WeightUnit.KILOGRAM)));
        assertEquals(2.0, wa.divide(wb), 1e-6);

        Quantity<VolumeUnit> va = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> vb = new Quantity<>(5.0, VolumeUnit.LITRE);
        assertTrue(va.add(vb).equals(new Quantity<>(15.0, VolumeUnit.LITRE)));
        assertTrue(va.subtract(vb).equals(new Quantity<>(5.0, VolumeUnit.LITRE)));
        assertEquals(2.0, va.divide(vb), 1e-6);
    }

    @Test
    public void testImmutability_AfterAdd_ViaCentralizedHelper() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        a.add(b);
        assertTrue(a.equals(new Quantity<>(10.0, LengthUnit.FEET)));
    }

    @Test
    public void testArithmetic_Chain_Operations() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(a.add(b).subtract(c).equals(new Quantity<>(11.0, LengthUnit.FEET)));
    }
}