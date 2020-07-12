package com.springapi.assignment.Models.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
public class Orders implements Serializable {
    @Id
    private Integer orderId;

    private String orderItems;

    private Double totalAmount;
}
