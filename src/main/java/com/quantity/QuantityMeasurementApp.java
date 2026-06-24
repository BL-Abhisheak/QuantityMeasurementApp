package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.IQuantityMeasurementRepository;
import com.quantity.repository.QuantityMeasurementDatabaseRepository;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.service.QuantityMeasurementServiceImpl;
import com.quantity.util.DatabaseInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		DatabaseInitializer.init();

		IQuantityMeasurementRepository repo =
				new QuantityMeasurementDatabaseRepository();

		IQuantityMeasurementService service =
				new QuantityMeasurementServiceImpl(repo);

		QuantityMeasurementController controller =
				new QuantityMeasurementController(service);

		controller.performComparison(
				new QuantityDTO(1, "FEET", "LENGTH"),
				new QuantityDTO(12, "INCHES", "LENGTH")
		);
	}
}