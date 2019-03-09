package com.example.foodcenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RegTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int tableNumber;


    private int count;


    private  String sendDateTable;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private User user;
}
