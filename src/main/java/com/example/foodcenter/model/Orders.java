package com.example.foodcenter.model;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;



    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "costumerId")
    private User user;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "productId")
    private Menu menu;

    @NotNull
    private double totalPrice;


}
