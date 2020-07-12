package com.springapi.assignment.Controllers;

import com.springapi.assignment.Models.Common.Response;
import com.springapi.assignment.Models.DTOs.OrderDto;
import com.springapi.assignment.Models.Model.Orders;
import com.springapi.assignment.Models.Model.PriceModel;
import com.springapi.assignment.Models.Model.Products;
import com.springapi.assignment.Service.PriceCalculator.IPriceCalculatorService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/price")
public class PriceCalculationController {

    private final IPriceCalculatorService priceCalculatorService;

    public PriceCalculationController(IPriceCalculatorService priceCalculatorService) {
        this.priceCalculatorService = priceCalculatorService;
    }

    @PostMapping(path = "totalPriceCalculation")
    public Response calculatePrice(@RequestBody PriceModel pricemodel)
    {
        if(pricemodel == null){
            return Response.badRequest();
        } else {
            Double price = priceCalculatorService.calculatePrice(pricemodel);
            if(price != null) {
                return Response.ok().setPayload(price);
            } else {
                return Response.notFound();
            }
        }

    }

    @PostMapping(path = "orderWrite")
    public Response orderWrite(@RequestBody OrderDto order)
    {
        if(order == null){
            return Response.badRequest();
        } else {
            Orders value = priceCalculatorService.orderWrite(order);
            if(value != null) {
                return Response.ok().setPayload(value);
            } else {
                return Response.notFound();
            }
        }
    }

}
