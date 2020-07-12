package com.springapi.assignment.Repository.PriceCalculator;

import com.springapi.assignment.Models.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceCalculatorRepository extends JpaRepository<Products, Integer> {
}
