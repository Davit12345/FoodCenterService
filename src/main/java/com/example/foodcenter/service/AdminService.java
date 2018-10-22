package com.example.foodcenter.service;


import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.User;

import java.util.List;

public interface AdminService {

    void addManager(String  email) throws InterruptedException, NotFoundException;

    void deleteManager(String email) throws InterruptedException, NotFoundException;

    void blockUser(String email) throws NotFoundException, InterruptedException, AccessDeniedException;

    List<User> getAllManager() throws InterruptedException;




}
