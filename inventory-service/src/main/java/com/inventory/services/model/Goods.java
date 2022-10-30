package com.inventory.services.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Goods {

    private String name;

    private String brand;

    private String type;
}
