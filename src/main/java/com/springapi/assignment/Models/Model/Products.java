package com.springapi.assignment.Models.Model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productName;

    private Double cartonPrice;

    private Integer unitsPerCarton;

    private Double unitPrice;
}
