package com.example.foodcenter.service;

import com.example.foodcenter.exceptions.BadRequestException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Orders;
import com.example.foodcenter.model.User;

import java.util.List;

public interface OrderService {



    void addOrder(String email) throws InternalErrorException, BadRequestException;
   //manager
    List<Orders> getAllAnSendOrders();//socket
    void addPay(User user,double amount );
    void SendFood(int order_id);
}
