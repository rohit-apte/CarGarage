package com.ing.carapp.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    private String location;
    private List<Vehicle> vehicles;
}
