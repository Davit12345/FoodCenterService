package com.example.foodcenter.controller.forJsonModels;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class EmailAndCode {
    @Email
    @NotNull
    private String email;

    private String code;
}
