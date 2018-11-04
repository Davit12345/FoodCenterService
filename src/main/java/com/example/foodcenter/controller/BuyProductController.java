package com.example.foodcenter.controller;


import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.example.foodcenter.util.UrlConstants.ORDER_CONTROLLER_URL;

@RestController
@RequestMapping(ORDER_CONTROLLER_URL)
public class BuyProductController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ResponseEntity addProduct(Principal principal) throws InternalErrorException {

        orderService.addOrder(principal.getName());

        return ResponseEntity.ok("success buy any product pleas make the payment");
    }

}
