package com.example.foodcenter.controller;

import com.example.foodcenter.model.Orders;
import com.example.foodcenter.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManagerController {
 @Autowired
    private OrderService orderService;

    @GetMapping("/unSendOrders")
    public ResponseEntity getMenu() {

        List<Orders> ordersList=orderService.getAllAnSendOrders();

        return ResponseEntity.ok().body(ordersList);

    }


}
