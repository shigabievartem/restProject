package com.tsp.backbone.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Calendar;

/**
 * ДТО для взаимодействия внутри приложения и взаимодействия со смежными модулями
 */
@Data
@ApiModel(description = "backbone default DTO")
public class BackboneDTO {
    private Long id;
    private String name;
    private Boolean isOk;
    private Calendar lastUpdate;
}
