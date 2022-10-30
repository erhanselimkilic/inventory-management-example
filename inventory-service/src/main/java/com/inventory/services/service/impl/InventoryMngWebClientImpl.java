package com.inventory.services.service.impl;

import com.inventory.data.model.Inventory;
import com.inventory.data.model.Person;
import com.inventory.data.model.Title;
import com.inventory.data.model.Type;
import com.inventory.data.model.base.ResponseMessage;
import com.inventory.data.repository.PersonRepository;
import com.inventory.services.model.Employees;
import com.inventory.services.model.ErrorMessage;
import com.inventory.services.model.Goods;
import com.inventory.services.service.InventoryMngWebClient;
import com.inventory.services.service.dto.InventoryPersonRequestDTO;
import com.inventory.services.service.dto.InventoryPersonResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InventoryMngWebClientImpl implements InventoryMngWebClient {

    private final WebClient webClient;

    private final PersonRepository personRepository;

    public InventoryMngWebClientImpl(WebClient.Builder webClientBuilder, PersonRepository personRepository)
    {
        this.webClient = webClientBuilder.baseUrl("https://run.mocky.io").build();
        this.personRepository = personRepository;
    }

    @Override
    public Mono<ResponseMessage> persistInventoryPerson(InventoryPersonRequestDTO inventoryPersonRequestDTO) {

        return this.webClient
                .post()
                .uri(uriBuilder ->
                        uriBuilder.path(inventoryPersonRequestDTO.getPath()).build())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(inventoryPersonRequestDTO), InventoryPersonRequestDTO.class)
                .exchangeToMono(clientResponse -> {

                    if(clientResponse.statusCode().equals(HttpStatus.OK))
                    {
                        return clientResponse.bodyToMono(InventoryPersonResponseDTO.class)
                                .map(value -> {

                                    //List<Person> people = new ArrayList<>();

                                    for(Employees emp : value.getEmployees())
                                    {
                                        Person person = new Person();
                                        person.setName(emp.getName());
                                        person.setSurname(emp.getSurname());

                                        switch (emp.getTitle())
                                        {
                                            case "Prof" -> person.setTitle(Title.PROF);
                                            case "Manager" -> person.setTitle(Title.MANAGER);
                                            case "Assistant" -> person.setTitle(Title.ASSISTANT);
                                            case "Trainee" -> person.setTitle(Title.TRAINEE);
                                        }

                                        List<Goods> goodsList = emp.getGoods();

                                        for(Goods goods : goodsList)
                                        {
                                            Inventory inventory = new Inventory();
                                            inventory.setPerson(person);
                                            inventory.setName(goods.getName());
                                            inventory.setBrand(goods.getBrand());

                                            switch (goods.getType())
                                            {
                                                case "Laptop" -> inventory.setType(Type.LAPTOP);
                                                case "Mouse" -> inventory.setType(Type.MOUSE);
                                                case "Mobile" -> inventory.setType(Type.MOBILE);
                                                case "Usb" -> inventory.setType(Type.USB);
                                                case "Keyboard" -> inventory.setType(Type.KEYBOARD);
                                            }

                                            person.getInventories().add(inventory);
                                        }

                                        personRepository.save(person);

                                        //people.add(person);

                                    }

                                    //personRepository.saveAll(people);

                                    return ResponseMessage
                                            .builder()
                                            .isSuccess(Boolean.TRUE)
                                            .message("Transfer adımı başarılı bir şekilde tamamlanmıştır.")
                                            .build();
                                });
                    }
                    else if(clientResponse.statusCode().is5xxServerError())
                    {
                        return clientResponse.bodyToMono(ErrorMessage.class)
                                .flatMap(errorMessage -> {

                                    ResponseMessage message = ResponseMessage
                                            .builder()
                                            .isSuccess(Boolean.FALSE)
                                            .message("code: " + errorMessage.getErrors().getCode() + " - message: " + errorMessage.getErrors().getMessage())
                                            .build();

                                    return Mono.just(message);
                                }
                        );
                    }
                    else
                        return clientResponse.createException().flatMap(Mono::error);
                });

    }
}
