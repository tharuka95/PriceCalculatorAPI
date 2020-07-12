package com.springapi.assignment.Models.Model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceModel implements Serializable {

    private Integer productId;

    private Integer units;
}
