package com.example.foodcenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Transmitted {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private  User user;

    @ManyToOne
    @JoinColumn(name = "CartId")
    @JsonIgnore
    private Cart cart;


    private  double amount;
}
