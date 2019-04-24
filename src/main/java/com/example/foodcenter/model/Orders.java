package com.example.foodcenter.model;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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


    private boolean send;

    @OneToOne
    private  Address address;

    @OneToOne
    private  RegTable table;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RegTable getTable() {
        return table;
    }

    public void setTable(RegTable table) {
        this.table = table;
    }
}
