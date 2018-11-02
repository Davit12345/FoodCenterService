package com.example.foodcenter.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int quantity;

    @ManyToOne
    @JoinColumn(name = "costumerId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Menu menu;

}
