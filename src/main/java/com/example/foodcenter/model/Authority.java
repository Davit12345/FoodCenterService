package com.example.foodcenter.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
public class Authority {

    @Id
    private int id;

    @NotNull
    private String name;




}
