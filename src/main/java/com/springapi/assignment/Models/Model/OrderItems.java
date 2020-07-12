package com.springapi.assignment.Models.Model;

import lombok.Data;

@Data
public class OrderItems {

    private Integer productId;

    private String productName;

    private Integer units;

    private Double totalPrice;
}
