package com.inventory.services.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Employees {

    private String name;

    private String surname;

    private String title;

    private List<Goods> goods;
}
