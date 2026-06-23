package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    @Test
    public void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        Quantity<TemperatureUnit> a = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        assertTrue(a.equals(b));
    }

    @Test
    public void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        Quantity<TemperatureUnit> a = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> b = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        assertTrue(a.equals(b));
    }



    @Test
    public void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> a = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        assertTrue(a.equals(a));
    }

    @Test
    public void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        Quantity<TemperatureUnit> a = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = a.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(122.0, result.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        Quantity<TemperatureUnit> a = new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> result = a.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(50.0, result.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_SameUnit() {
        Quantity<TemperatureUnit> a = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = a.convertTo(TemperatureUnit.CELSIUS);
        assertEquals(100.0, result.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_ZeroValue() {
        Quantity<TemperatureUnit> a = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = a.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(32.0, result.getValue(), EPSILON);
    }

    @Test
    public void testTemperatureConversion_NegativeValues() {
        Quantity<TemperatureUnit> a = new Quantity<>(-20.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> result = a.convertTo(TemperatureUnit.FAHRENHEIT);
        assertEquals(-4.0, result.getValue(), EPSILON);
    }


    @Test
    public void testTemperatureVsLengthIncompatibility() {
        Quantity<TemperatureUnit> a = new Quantity<>(100.0, TemperatureUnit.CELSIUS);
        Quantity<LengthUnit> b = new Quantity<>(100.0, LengthUnit.FEET);
        assertFalse(a.equals(b));
    }

    @Test
    public void testTemperatureVsWeightIncompatibility() {
        Quantity<TemperatureUnit> a = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> b = new Quantity<>(50.0, WeightUnit.KILOGRAM);
        assertFalse(a.equals(b));
    }

    @Test
    public void testTemperatureVsVolumeIncompatibility() {
        Quantity<TemperatureUnit> a = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<VolumeUnit> b = new Quantity<>(25.0, VolumeUnit.LITRE);
        assertFalse(a.equals(b));
    }

    @Test
    public void testOperationSupportMethods_TemperatureUnitAddition() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_LengthUnitAddition() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_WeightUnitDivision() {
        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
    }

    @Test
    public void testTemperatureBackwardCompatibility_LengthStillWorks() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCH);
        assertTrue(a.equals(b));
    }

}