package com.springapi.assignment.Service.PriceCalculator;

import com.google.gson.Gson;
import com.springapi.assignment.Models.DTOs.OrderDto;
import com.springapi.assignment.Models.Model.Orders;
import com.springapi.assignment.Models.Model.PriceModel;
import com.springapi.assignment.Models.Model.Products;
import com.springapi.assignment.Repository.PriceCalculator.OrderRepository;
import com.springapi.assignment.Repository.PriceCalculator.PriceCalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class PriceCalculatorService implements IPriceCalculatorService {
    private final PriceCalculatorRepository priceCalculatorRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PriceCalculatorService(PriceCalculatorRepository priceCalculatorRepository, OrderRepository orderRepository) {
        this.priceCalculatorRepository = priceCalculatorRepository;
        this.orderRepository = orderRepository;
    }

    public Double calculatePrice(PriceModel priceModel) {
        Double totalPrice = 0.0;
        Optional<Products> optional = priceCalculatorRepository.findById(priceModel.getProductId());
        if(optional.isPresent()){
            Products product = optional.get();
            Integer unitsInCarton = product.getUnitsPerCarton();
            if(priceModel.getUnits() < unitsInCarton) {
                totalPrice = priceModel.getUnits() * (product.getUnitPrice()* 130 / 100);
            } else {
                Integer boxCount = priceModel.getUnits() / unitsInCarton;
                Integer itemCount = priceModel.getUnits() % unitsInCarton;
                if (boxCount >= 3) {
                    totalPrice = (product.getCartonPrice() * 90 / 100) * boxCount;
                } else {
                    totalPrice = product.getCartonPrice() * boxCount;
                }
                totalPrice += itemCount * (product.getUnitPrice()* 130 / 100);
            }
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public Orders orderWrite(OrderDto order) {
        Random rand = new Random();
        String orderItems = new Gson().toJson(order.getOrderItems());
        Orders newOrder = new Orders();
        newOrder.setOrderId(rand.nextInt(1000000));
        newOrder.setTotalAmount(order.getTotalAmount());
        newOrder.setOrderItems(orderItems);
        Orders orderWrite = orderRepository.saveAndFlush(newOrder);
        return orderWrite;
    }
}
