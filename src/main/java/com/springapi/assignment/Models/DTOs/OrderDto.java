package com.springapi.assignment.Models.DTOs;

import com.springapi.assignment.Models.Model.OrderItems;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class OrderDto implements Serializable {

    private Integer orderId;

    private ArrayList<OrderItems> orderItems;

    private Double totalAmount;
}
