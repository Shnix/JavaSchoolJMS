package ru.krayale.dto;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class SystemInfoBean implements Serializable {

    private List<OrderDto> orderDtoList;

    private Integer availableDriversCount;

    private Integer unavailableDriversCount;

    private Integer availablePlainsCount;

    private Integer unavailablePlainsCount;

    private Integer availableBoatsCount;

    private Integer unavailableBoatsCount;

    private Integer brokenPlainsCount;

    private Integer brokenBoatsCount;


}
