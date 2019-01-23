package com.example.foodcenter.controller;


import com.example.foodcenter.controller.forJsonModels.Item;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;
import com.example.foodcenter.service.OrderItemService;
import com.example.foodcenter.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(UrlConstants.CHOOSE_ORDER_CONTROLLER_URL)
@CrossOrigin
public class OrderChooseController {

    @Autowired
    private OrderItemService orderItemService;


    @PostMapping
    public ResponseEntity addProduct(Principal principal, @Valid @RequestBody Item item) throws InternalErrorException, TeapotException {

        System.out.println(item.getName() + "" + item.getQuantity());
        orderItemService.addOrderItem(principal.getName(), item);

        return ResponseEntity.ok("success added  product in your list");

    }


    @GetMapping
    public ResponseEntity getUserChoose(Principal principal) throws InternalErrorException {

        List<OrderItem> orderItemList = orderItemService.getAllItemOneCostumer(principal.getName());
        return ResponseEntity.ok().body(orderItemList);
    }


}
