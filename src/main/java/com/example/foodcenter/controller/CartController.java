package com.example.foodcenter.controller;

import com.example.foodcenter.controller.forJsonModels.CartInfo;
import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.Cart;
import com.example.foodcenter.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static com.example.foodcenter.util.UrlConstants.CART_CONTROLLER_URL;

@Controller
@RequestMapping(value = CART_CONTROLLER_URL)
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping
    public  ResponseEntity  addCart(Principal principal,@Valid @RequestBody CartInfo cartInfo) throws InternalErrorException, DuplicateDataException {
        cartService.addCart(cartInfo,principal.getName());

        return  ResponseEntity.ok("success add cart");
    }


    @GetMapping("/{code}")
    public  ResponseEntity  addCart(@PathVariable(value = "code") String code, Principal principal) throws InternalErrorException, NotFoundException, AccessDeniedException {
        cartService.deleteCart(principal.getName(),code);

        return  ResponseEntity.ok("success delete  cart");
    }


    @GetMapping
    public  ResponseEntity  getAllCartByUser( Principal principal) throws InternalErrorException {

       List<Cart> cartList= cartService.getCartByUser(principal.getName());

        return  ResponseEntity.ok().body(cartList);
    }

}
