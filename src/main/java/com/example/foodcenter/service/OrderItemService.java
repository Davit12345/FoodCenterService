package com.example.foodcenter.service;


import com.example.foodcenter.controller.forJsonModels.Item;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;

import java.util.List;

public interface OrderItemService  {

   void addOrderItem(String email, Item item) throws InternalErrorException, TeapotException;
    List<OrderItem> getAllItemOneCostumer(String email) throws InternalErrorException;
    void updateOneProduct(User user,String name,OrderItem orderItem);
    void deleteOneProduct(int id, String name) throws InternalErrorException, NotFoundException;
}
