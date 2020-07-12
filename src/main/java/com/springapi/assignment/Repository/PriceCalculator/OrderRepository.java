package com.springapi.assignment.Repository.PriceCalculator;

import com.springapi.assignment.Models.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
