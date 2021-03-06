package com.springapi.assignment;

import com.springapi.assignment.Controllers.PriceCalculationController;
import com.springapi.assignment.Models.Model.PriceModel;
import com.springapi.assignment.Service.PriceCalculator.PriceCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AssignmentApplicationTests {
    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @Test
    void contextLoads() {
        Double val1 = 22.7;
        Double val2 = 56.875;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(1);
        mockPrice.setUnits(5);
        Double value = priceCalculatorService.calculatePrice(mockPrice);
        //assertThat(value).isEqualTo(val1);
        assertThat(value).isEqualTo(val2);
    }

    @Test
    void cartonPriceHorseShoe() {
        Double val1 = 825.0;
        Double val2 = 56.875;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(2);
        mockPrice.setUnits(5);
        Double value = priceCalculatorService.calculatePrice(mockPrice);
        assertThat(value).isEqualTo(val1);
        //assertThat(value).isEqualTo(val2);
    }

    @Test
    void unitsLessThanCarton() {
        Double val1 = ((825.0/5)/10*13)*2;
        Double val2 = (825.0/5)*2;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(2);
        mockPrice.setUnits(2);
        Double value = priceCalculatorService.calculatePrice(mockPrice);
        assertThat(value).isEqualTo(val1);
        //assertThat(value).isEqualTo(val2);
    }

    @Test
    void unitsMoreThanSingleCarton() {
        Double val1 = (825.0)*2;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(2);
        mockPrice.setUnits(10);
        Double value = priceCalculatorService.calculatePrice(mockPrice);
        assertThat(value).isEqualTo(val1);
        //assertThat(value).isEqualTo(val2);
    }

    @Test
    void unitsMoreThanSingleCartonExceedDiscoutCartonNumber() {
        Double val1 = ((825.0)*4)/100*90;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(2);
        mockPrice.setUnits(20);
        Double value = priceCalculatorService.calculatePrice(mockPrice);
        assertThat(value).isEqualTo(val1);
    }

    @Test
    void unitsMoreThanSingleCartonExceedDiscoutCartonNumberThereAreSomeunitsLeft() {
        Double val1 = ((825.0)*4)/100*90+((825.0/5)/10*13)*3;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(2);
        mockPrice.setUnits(23);
        Double value = priceCalculatorService.calculatePrice(mockPrice);
        assertThat(value).isEqualTo(val1);
    }

}
