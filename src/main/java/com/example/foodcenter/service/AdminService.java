package com.example.foodcenter.service;


import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.User;

import java.util.List;

public interface AdminService {

    void addManager(String  email) throws InterruptedException, NotFoundException, InternalErrorException;

    void deleteManager(String email) throws InterruptedException, NotFoundException, InternalErrorException;

    void blockUser(String email) throws NotFoundException, InterruptedException, AccessDeniedException, InternalErrorException;

    List<User> getAllManager() throws InterruptedException, InternalErrorException;




}
