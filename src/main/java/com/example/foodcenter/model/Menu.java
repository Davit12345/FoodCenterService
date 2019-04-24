package com.example.foodcenter.model;

import com.example.foodcenter.model.enums.MenuItems;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.File;

@Entity
@Data
@EqualsAndHashCode
@ToString
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Min(value = 0, message = "The product price must not be less than Zero!")
    private double price;


    private String info;

    private String imageUrl;

    private File image;

    @Enumerated(EnumType.ORDINAL)
    private MenuItems menuItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public MenuItems getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItems menuItem) {
        this.menuItem = menuItem;
    }
}
