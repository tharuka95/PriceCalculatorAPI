package com.springapi.assignment.Models.Model;

import lombok.Data;

@Data
public class ProductTable {

    private String productName;

    private Double cartonPrice;

    private Integer unitsPerCarton;

    private Integer numberOfUnits;

    private Double totalPrice;
}
