package com.springapi.assignment.Controllers;

import com.google.gson.Gson;
import com.springapi.assignment.Models.Model.PriceModel;
import com.springapi.assignment.Service.PriceCalculator.PriceCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes={PriceCalculationController.class})
@WebMvcTest(value=PriceCalculationController.class)
class PriceCalculationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceCalculatorService priceCalculatorService;
    @Test
    void calculatePrice() throws Exception {

        Double val1 = 22.7;
        PriceModel mockPrice = new PriceModel();
        mockPrice.setProductId(1);
        mockPrice.setUnits(5);
        String inputJson = new Gson().toJson(mockPrice);
        Mockito.when(priceCalculatorService.calculatePrice(Mockito.any(PriceModel.class))).thenReturn(val1);
        RequestBuilder request = MockMvcRequestBuilders.post("/totalPriceCalculation")
                .accept(MediaType.APPLICATION_JSON).content(inputJson);
        MvcResult result = mvc.perform(request).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();
        String outputResponse = response.getContentAsString();

        assertEquals(val1,outputResponse);
    }
}