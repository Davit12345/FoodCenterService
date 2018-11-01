package com.example.foodcenter.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
