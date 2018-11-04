package com.example.foodcenter.service;

import com.example.foodcenter.controller.forJsonModels.CartInfo;
import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.Cart;
import com.example.foodcenter.model.User;

import java.util.List;

public interface CartService {

    void addCart(CartInfo cartInfo, String email) throws InternalErrorException, DuplicateDataException;

    void deleteCart(String email, String cartCode) throws InternalErrorException, NotFoundException, AccessDeniedException;

    List<Cart> getCartByUser(String email) throws InternalErrorException;
}
