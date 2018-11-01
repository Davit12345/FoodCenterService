package com.example.foodcenter.model;

import com.example.foodcenter.model.enums.MenuItems;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Min(value = 0, message = "The product price must not be less than Zero!")
    private int price;


    private String info;

    @Enumerated(EnumType.ORDINAL)
    private MenuItems menuItem;


}
