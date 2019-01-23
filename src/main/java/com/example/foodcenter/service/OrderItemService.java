package com.example.foodcenter.service;


import com.example.foodcenter.controller.forJsonModels.Item;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;

import java.util.List;

public interface OrderItemService  {

   void addOrderItem(String email, Item item) throws InternalErrorException, TeapotException;

    List<OrderItem> getAllItemOneCostumer(String email) throws InternalErrorException;

    void deleteOneProduct(User user,String name,OrderItem orderItem);
    void updateOneProduct(User user,String name,OrderItem orderItem);

}
