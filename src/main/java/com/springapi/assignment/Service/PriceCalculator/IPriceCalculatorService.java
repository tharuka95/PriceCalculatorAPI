package com.springapi.assignment.Service.PriceCalculator;

import com.springapi.assignment.Models.DTOs.OrderDto;
import com.springapi.assignment.Models.Model.Orders;
import com.springapi.assignment.Models.Model.PriceModel;

public interface IPriceCalculatorService {

    Double calculatePrice(PriceModel priceModel);

    Orders orderWrite(OrderDto order);

}
