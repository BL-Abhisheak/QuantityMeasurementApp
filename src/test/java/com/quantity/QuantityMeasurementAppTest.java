package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.exception.QuantityMeasurementException;
import com.quantity.repository.IQuantityMeasurementRepository;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    public void setUp() {
        repository = QuantityMeasurementCacheRepository.getInstance();
        repository.deleteAll();
        service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }


    @Test
    public void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(1.0, "FEET", "LengthUnit");
        assertTrue(service.compare(a, b));
    }

    @Test
    public void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(12.0, "INCH", "LengthUnit");
        assertTrue(service.compare(a, b));
    }

    @Test
    public void testService_CompareInequality_DifferentValues_Fails() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(10.0, "INCH", "LengthUnit");
        assertFalse(service.compare(a, b));
    }

    @Test
    public void testService_Compare_DifferentCategories_ThrowsException() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        assertThrows(QuantityMeasurementException.class, () -> service.compare(a, b));
    }


    @Test
    public void testService_Convert_FeetToInch_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO result = service.convert(a, "INCH");
        assertEquals(12.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Convert_InchToFeet_Success() {
        QuantityDTO a = new QuantityDTO(12.0, "INCH", "LengthUnit");
        QuantityDTO result = service.convert(a, "FEET");
        assertEquals(1.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Convert_FeetToYards_Success() {
        QuantityDTO a = new QuantityDTO(3.0, "FEET", "LengthUnit");
        QuantityDTO result = service.convert(a, "YARDS");
        assertEquals(1.0, result.getValue(), 1e-2);
    }


    @Test
    public void testService_Add_FeetAndInch_InFeet_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(12.0, "INCH", "LengthUnit");
        QuantityDTO result = service.add(a, b, "FEET");
        assertEquals(2.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Add_FeetAndFeet_InFeet_Success() {
        QuantityDTO a = new QuantityDTO(2.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(3.0, "FEET", "LengthUnit");
        QuantityDTO result = service.add(a, b, "FEET");
        assertEquals(5.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Add_NoTargetUnit_UsesFirstUnit() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO result = service.add(a, b);
        assertEquals(2.0, result.getValue(), 1e-2);
        assertEquals("FEET", result.getUnit());
    }


    @Test
    public void testService_Subtract_FeetAndInch_Success() {
        QuantityDTO a = new QuantityDTO(10.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(6.0, "INCH", "LengthUnit");
        QuantityDTO result = service.subtract(a, b);
        assertEquals(9.5, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Subtract_SameUnit_Success() {
        QuantityDTO a = new QuantityDTO(5.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(2.0, "FEET", "LengthUnit");
        QuantityDTO result = service.subtract(a, b);
        assertEquals(3.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Divide_Success() {
        QuantityDTO a = new QuantityDTO(10.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(2.0, "FEET", "LengthUnit");
        assertEquals(5.0, service.divide(a, b), 1e-6);
    }

    @Test
    public void testService_Divide_ByZero_ThrowsException() {
        QuantityDTO a = new QuantityDTO(10.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(0.0, "FEET", "LengthUnit");
        assertThrows(QuantityMeasurementException.class, () -> service.divide(a, b));
    }


    @Test
    public void testService_Weight_CompareKilogramAndGram_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityDTO b = new QuantityDTO(1000.0, "GRAM", "WeightUnit");
        assertTrue(service.compare(a, b));
    }

    @Test
    public void testService_Weight_Convert_KilogramToGram_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityDTO result = service.convert(a, "GRAM");
        assertEquals(1000.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Weight_Add_KilogramAndGram_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityDTO b = new QuantityDTO(500.0, "GRAM", "WeightUnit");
        QuantityDTO result = service.add(a, b, "KILOGRAM");
        assertEquals(1.5, result.getValue(), 1e-2);
    }


    @Test
    public void testService_Volume_CompareLitreAndMillilitre_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "LITRE", "VolumeUnit");
        QuantityDTO b = new QuantityDTO(1000.0, "MILLILITRE", "VolumeUnit");
        assertTrue(service.compare(a, b));
    }

    @Test
    public void testService_Volume_Convert_LitreToMillilitre_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "LITRE", "VolumeUnit");
        QuantityDTO result = service.convert(a, "MILLILITRE");
        assertEquals(1000.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Volume_Add_LitreAndMillilitre_Success() {
        QuantityDTO a = new QuantityDTO(1.0, "LITRE", "VolumeUnit");
        QuantityDTO b = new QuantityDTO(500.0, "MILLILITRE", "VolumeUnit");
        QuantityDTO result = service.add(a, b, "LITRE");
        assertEquals(1.5, result.getValue(), 1e-2);
    }


    @Test
    public void testService_Temperature_CompareEquality_CelsiusAndFahrenheit() {
        QuantityDTO a = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO b = new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit");
        assertTrue(service.compare(a, b));
    }

    @Test
    public void testService_Temperature_Convert_CelsiusToFahrenheit_Success() {
        QuantityDTO a = new QuantityDTO(100.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO result = service.convert(a, "FAHRENHEIT");
        assertEquals(212.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Temperature_Convert_FahrenheitToCelsius_Success() {
        QuantityDTO a = new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit");
        QuantityDTO result = service.convert(a, "CELSIUS");
        assertEquals(0.0, result.getValue(), 1e-2);
    }

    @Test
    public void testService_Temperature_Add_ThrowsUnsupportedOperation() {
        QuantityDTO a = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO b = new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit");
        assertThrows(QuantityMeasurementException.class, () -> service.add(a, b));
    }


    @Test
    public void testLayerSeparation_ServiceIndependence() {
        IQuantityMeasurementService s = new QuantityMeasurementServiceImpl(repository);
        QuantityDTO a = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityDTO b = new QuantityDTO(1000.0, "GRAM", "WeightUnit");
        assertTrue(s.compare(a, b));
    }

    @Test
    public void testLayerSeparation_RepositorySavesOnSuccess() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(12.0, "INCH", "LengthUnit");
        service.compare(a, b);
        assertTrue(repository.getTotalCount() > 0);
    }

    @Test
    public void testLayerSeparation_RepositorySavesOnError() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        assertThrows(QuantityMeasurementException.class, () -> service.compare(a, b));
        assertTrue(repository.getTotalCount() > 0);
    }

    @Test
    public void testLayerSeparation_DeleteAll_ClearsRepository() {
        QuantityDTO a = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO b = new QuantityDTO(1.0, "FEET", "LengthUnit");
        service.compare(a, b);
        repository.deleteAll();
        assertEquals(0, repository.getTotalCount());
    }
}