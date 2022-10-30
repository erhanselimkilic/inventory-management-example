package com.inventory.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "INVENTORY")
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    private Person person;
}
