
package com.calculator.calculator_backend.service;

import java.util.List;
import com.calculator.calculator_backend.entity.Calculation;
import com.calculator.calculator_backend.repository.CalculationRepository;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public Calculation saveCalculation(Calculation calculation) {
        return calculationRepository.save(calculation);
    }

    public List<Calculation> getAllCalculations() {
        return calculationRepository.findAll();
    }

    public void deleteAllCalculation() {
        calculationRepository.deleteAll();
    }
}
