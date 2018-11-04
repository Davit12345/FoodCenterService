package com.example.foodcenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int quantity;


    @NotNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "costumerId")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "productId")
    private Menu menu;

}
