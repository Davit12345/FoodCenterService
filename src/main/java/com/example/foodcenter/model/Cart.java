package com.example.foodcenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @NotNull
    @Column(unique = true)
    String cartCode;

    @NotNull
    @Size(min = 8,message = "Password must be more then 8 characters")
    String cartPassword;

    @NotNull
    @Size(min = 8,message = "Password must be more then 8 characters")
    String cartAuthKey;

    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
        this.cartCode = cartCode;
    }

    public String getCartPassword() {
        return cartPassword;
    }

    public void setCartPassword(String cartPassword) {
        this.cartPassword = cartPassword;
    }

    public String getCartAuthKey() {
        return cartAuthKey;
    }

    public void setCartAuthKey(String cartAuthKey) {
        this.cartAuthKey = cartAuthKey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
