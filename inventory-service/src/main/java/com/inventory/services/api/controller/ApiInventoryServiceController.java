package com.inventory.services.api.controller;

import com.inventory.data.model.base.ResponseMessage;
import com.inventory.services.api.constant.ApiConstant;
import com.inventory.services.service.InventoryMngWebClient;
import com.inventory.services.service.dto.InventoryPersonRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = ApiConstant.MAIN_API_PATH)
public class ApiInventoryServiceController {

    private final InventoryMngWebClient inventoryMngWebClient;

    public ApiInventoryServiceController(InventoryMngWebClient inventoryMngWebClient)
    {
        this.inventoryMngWebClient = inventoryMngWebClient;
    }

    @PostMapping(value = "/transfer")
    public ResponseEntity<Mono<ResponseMessage>> persist(@RequestBody InventoryPersonRequestDTO dto)
    {
        //Success
        Mono<ResponseMessage> result = inventoryMngWebClient.persistInventoryPerson(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
