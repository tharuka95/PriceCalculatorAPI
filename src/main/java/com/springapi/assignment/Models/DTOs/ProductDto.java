package com.springapi.assignment.Models.DTOs;

import com.springapi.assignment.Models.Model.Products;
import lombok.Data;

@Data
public class ProductDto {

    private Integer id;

    private String productName;

    private Double cartonPrice;

    private Integer numberOfUnits;

    private Double totalPrice;


    public ProductDto() {
    }

}
