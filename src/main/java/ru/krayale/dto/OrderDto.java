package ru.krayale.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderDto implements Serializable {

    private String orderId;

    private String startCity;

    private String destinationCity;

    private String vehicleName;

    private String cargoName;

    private Integer cargoWeight;
}
