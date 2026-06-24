package com.quantity;

import com.quantity.unit.LengthUnit;
import com.quantity.unit.TemperatureUnit;
import com.quantity.unit.VolumeUnit;
import com.quantity.unit.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    @Test
    public void testEquality_LitreToLitre_SameValue() {

        assertTrue(
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).equals(
                        new Quantity<>(
                                1.0,
                                VolumeUnit.LITRE
                        )
                )
        );
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {

        assertTrue(
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).equals(
                        new Quantity<>(
                                1000.0,
                                VolumeUnit.MILLILITRE
                        )
                )
        );
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {

        assertTrue(
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                ).equals(
                        new Quantity<>(
                                3.78541,
                                VolumeUnit.LITRE
                        )
                )
        );
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<LengthUnit> length =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        assertFalse(volume.equals(length));
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<WeightUnit> weight =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(volume.equals(weight));
    }

    @Test
    public void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).convertTo(
                        VolumeUnit.MILLILITRE
                );

        assertEquals(
                1000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testConversion_MillilitreToLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                ).convertTo(
                        VolumeUnit.LITRE
                );

        assertEquals(
                1.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                ).convertTo(
                        VolumeUnit.LITRE
                );

        assertEquals(
                3.78541,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testConversion_LitreToGallon() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                ).convertTo(
                        VolumeUnit.GALLON
                );

        assertEquals(
                1.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testConversion_RoundTrip() {

        Quantity<VolumeUnit> original =
                new Quantity<>(
                        1.5,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> result =
                original.convertTo(
                        VolumeUnit.MILLILITRE
                ).convertTo(
                        VolumeUnit.LITRE
                );

        assertEquals(
                original.getValue(),
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).add(
                        new Quantity<>(
                                2.0,
                                VolumeUnit.LITRE
                        )
                );

        assertEquals(
                3.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).add(
                        new Quantity<>(
                                1000.0,
                                VolumeUnit.MILLILITRE
                        )
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_CrossUnit_GallonPlusLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                ).add(
                        new Quantity<>(
                                3.78541,
                                VolumeUnit.LITRE
                        )
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                ).add(
                        new Quantity<>(
                                1000.0,
                                VolumeUnit.MILLILITRE
                        ),
                        VolumeUnit.MILLILITRE
                );

        assertEquals(
                2000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gallon() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                ).add(
                        new Quantity<>(
                                3.78541,
                                VolumeUnit.LITRE
                        ),
                        VolumeUnit.GALLON
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_WithZero() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ).add(
                        new Quantity<>(
                                0.0,
                                VolumeUnit.MILLILITRE
                        )
                );

        assertEquals(
                5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_NegativeValues() {

        Quantity<VolumeUnit> result =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ).add(
                        new Quantity<>(
                                -2000.0,
                                VolumeUnit.MILLILITRE
                        )
                );

        assertEquals(
                3.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testVolumeUnitEnum_LitreConstant() {

        assertEquals(
                1.0,
                VolumeUnit.LITRE.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testVolumeUnitEnum_GallonConstant() {

        assertEquals(
                3.78541,
                VolumeUnit.GALLON.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_MillilitreToLitre() {

        assertEquals(
                1.0,
                VolumeUnit.MILLILITRE
                        .convertToBaseUnit(1000.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_LitreToGallon() {

        assertEquals(
                1.0,
                VolumeUnit.GALLON
                        .convertFromBaseUnit(3.78541),
                EPSILON
        );
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit() {

        assertTrue(
                new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT))
        );
    }

    @Test
    public void testTemperatureConversion() {

        Quantity<TemperatureUnit> result =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(212.0, result.getValue(), 0.01);
    }

    @Test
    public void testTemperatureVsLength_NotEqual() {

        assertFalse(
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .equals(new Quantity<>(100.0, LengthUnit.FEET))
        );
    }
}