package com.example.foodcenter.controller.forJsonModels;

import com.example.foodcenter.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CartInfo {


    @NotNull
    String cartCode;

    @NotNull
    @Size(min = 8,message = "Password must be more then 8 characters")
    String cartPassword;

}
