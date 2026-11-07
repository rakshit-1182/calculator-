
package com.calculator.calculator_backend.repository;

import com.calculator.calculator_backend.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {

}
