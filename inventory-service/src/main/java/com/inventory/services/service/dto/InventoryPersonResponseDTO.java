package com.inventory.services.service.dto;

import com.inventory.services.model.Employees;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InventoryPersonResponseDTO {

    List<Employees> employees;
}
