package com.ing.carapp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private int id;
    private String make;
    private String model;
    private int year_model;
    private BigDecimal price;
    private boolean licensed;
    private String date_added;
}
