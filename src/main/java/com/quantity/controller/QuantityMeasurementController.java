package com.quantity.controller;

import com.quantity.dto.QuantityDTO;
import com.quantity.dto.QuantityInputDTO;
import com.quantity.service.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public ResponseEntity<QuantityMeasurementDTO> compareQuantities(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response = service.compareQuantities(input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/convert")
    @Operation(summary = "Convert a quantity to a specified unit representation")
    public ResponseEntity<QuantityMeasurementDTO> convertQuantity(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response = service.convertQuantity(input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @Operation(summary = "Add two matching quantity specifications together")
    public ResponseEntity<QuantityMeasurementDTO> addQuantities(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response = service.addQuantities(input);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/operation/{operation}")
    @Operation(summary = "Get historical execution logs classified by action type")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationHistory(operation));
    }

    @GetMapping("/history/type/{measurementType}")
    @Operation(summary = "Get historical execution logs classified by metric class types")
    public ResponseEntity<List<QuantityMeasurementDTO>> getMeasurementTypeHistory(@PathVariable String measurementType) {
        return ResponseEntity.ok(service.getMeasurementTypeHistory(measurementType));
    }

    @GetMapping("/history/errored")
    @Operation(summary = "Retrieve complete run trace exceptions logs logs history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrorHistory() {
        return ResponseEntity.ok(service.getErrorHistory());
    }

    @GetMapping("/count/{operation}")
    @Operation(summary = "Retrieve safe counts for given operations executed without faults")
    public ResponseEntity<Long> getOperationCount(@PathVariable String operation) {
        return ResponseEntity.ok(service.getOperationCount(operation));
    }
}