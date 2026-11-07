package com.calculator.calculator_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calculations")

public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private double firstNumber;
    private double secondNumber;

    private String operator;
    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAtBeforeSaving() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    private double result;

    public Calculation() {
    }

    public Calculation(double firstNumber, double secondNumber, String operator, double result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
        this.result = result;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }


}
