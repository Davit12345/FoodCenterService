package com.example.foodcenter.service;


import com.example.foodcenter.controller.forJsonModels.EmailAndCode;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.User;

public interface UserService {


    void add(User user) throws DuplicateDataException, InternalErrorException;

    void verify(EmailAndCode emailAndCode) throws InternalErrorException, TeapotException;

    void sendRecoveringCode(String email) throws InternalErrorException;

    void changePassword(String email, String password, String recoveringCode) throws InternalErrorException;

    void resendVerificationCode(String email) throws NotFoundException, InternalErrorException;

    User getByEmail(String email) ;
}
