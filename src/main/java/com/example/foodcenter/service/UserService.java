package com.example.foodcenter.service;


import com.example.foodcenter.controller.forJsonModels.EmailAndCode;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.Authority;
import com.example.foodcenter.model.User;

import java.util.List;

public interface UserService {


    void addUser(User user) throws DuplicateDataException, InternalErrorException;

    void verify(EmailAndCode emailAndCode) throws InternalErrorException, TeapotException;

    void sendRecoveringCode(String email) throws InternalErrorException;

    void changePassword(String email, String password, String recoveringCode) throws InternalErrorException;

    void resendVerificationCode(String email) throws NotFoundException, InternalErrorException;

    List<Authority> getRoles(String email) throws InternalErrorException;

    User getByEmail(String email) ;
}
