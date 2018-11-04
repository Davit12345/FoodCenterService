package com.example.foodcenter.service;

import com.example.foodcenter.exceptions.BadRequestException;
import com.example.foodcenter.exceptions.InternalErrorException;

public interface OrderService {

  void  addOrder(String email) throws InternalErrorException, BadRequestException;

}
