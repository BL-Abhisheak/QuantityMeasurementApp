package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;
import com.quantity.unit.IMeasurable;
import com.quantity.unit.TemperatureUnit;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementApp {
	private final QuantityMeasurementController controller;

	public QuantityMeasurementApp() {
		QuantityMeasurementCacheRepository repository = QuantityMeasurementCacheRepository.getInstance();
		QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repository);
		this.controller = new QuantityMeasurementController(service);
	}

	public static void main(String[] args) {
		QuantityMeasurementApp app = new QuantityMeasurementApp();

		QuantityDTO feet = new QuantityDTO(1.0, "FEET", "LengthUnit");
		QuantityDTO inches = new QuantityDTO(12.0, "INCH", "LengthUnit");
		app.controller.performComparison(feet, inches);
		app.controller.performConversion(feet, "INCH");
		app.controller.performAddition(feet, inches, "FEET");
		app.controller.performSubtraction(new QuantityDTO(10.0, "FEET", "LengthUnit"),
				new QuantityDTO(6.0, "INCH", "LengthUnit"));
		app.controller.performDivision(new QuantityDTO(10.0, "FEET", "LengthUnit"),
				new QuantityDTO(2.0, "FEET", "LengthUnit"));

		QuantityDTO celsius = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
		QuantityDTO fahrenheit = new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit");
		app.controller.performComparison(celsius, fahrenheit);
		app.controller.performConversion(celsius, "FAHRENHEIT");
		app.controller.performAddition(celsius, fahrenheit);
	}
}