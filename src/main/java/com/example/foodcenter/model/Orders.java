package com.example.foodcenter.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Size(min = 1)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "costumerId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Menu menu;

    private double totalPrice;


}
