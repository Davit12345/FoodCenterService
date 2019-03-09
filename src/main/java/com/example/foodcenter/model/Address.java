package com.example.foodcenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;


    private  String city;

    private  String street;

    private  String home;

    private  String sendDateHome;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private User user;

}
