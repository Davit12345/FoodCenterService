package com.example.foodcenter.controller.forJsonModels;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Item {
    @NotNull
    private String name;

    @NotNull
    @Min(value = 0, message = "The  quantity must not be less than Zero!")
    private int quantity;
}
