package com.ing.carapp.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDetails {
    private String warehouseId;
    private String warehouseName;
    private Double latitude;
    private Double longitude;
    private String location;
    private int carId;
    private String make;
    private String model;
    private int year_model;
    private BigDecimal price;
    private boolean licensed;
    private String date_added;
}
