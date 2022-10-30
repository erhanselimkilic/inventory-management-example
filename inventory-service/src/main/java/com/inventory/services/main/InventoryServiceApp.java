package com.inventory.services.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.inventory.data.model"})
@ComponentScan(basePackages = {"com.inventory.services","com.inventory.data"})
@EnableJpaRepositories(basePackages = {"com.inventory.data.repository"})
public class InventoryServiceApp {

    public static void main(String[] args)
    {
        SpringApplication.run(InventoryServiceApp.class, args);
    }
}
