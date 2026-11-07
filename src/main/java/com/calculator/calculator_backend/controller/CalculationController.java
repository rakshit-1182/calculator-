package com.calculator.calculator_backend.controller;

import com.calculator.calculator_backend.entity.Calculation;
import com.calculator.calculator_backend.service.CalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/calculations")
@CrossOrigin(origins = "*")

public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping
    public Calculation saveCalculation(@RequestBody Calculation calculation) {
        return calculationService.saveCalculation(calculation);
    }
    @GetMapping
    public List<Calculation> getAllCalculations() {
        return calculationService.getAllCalculations();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCalculations() {
        calculationService.deleteAllCalculation();
        return ResponseEntity.noContent().build();
    }

}
