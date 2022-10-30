package com.inventory.services.service;

import com.inventory.data.model.base.ResponseMessage;
import com.inventory.services.service.dto.InventoryPersonRequestDTO;
import reactor.core.publisher.Mono;

public interface InventoryMngWebClient {

    Mono<ResponseMessage> persistInventoryPerson(InventoryPersonRequestDTO inventoryPersonRequestDTO);

}
