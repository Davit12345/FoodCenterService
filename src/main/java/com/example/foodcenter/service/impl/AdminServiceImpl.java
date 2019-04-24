package com.example.foodcenter.service.impl;


import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.Authority;
import com.example.foodcenter.model.Transmitted;
import com.example.foodcenter.model.User;
import com.example.foodcenter.model.enums.Status;
import com.example.foodcenter.repository.AuthorityRepository;
import com.example.foodcenter.repository.UserRepository;
import com.example.foodcenter.service.AdminService;
import com.example.foodcenter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public void addManager(String email) throws  NotFoundException, InternalErrorException {
        try {
            User user = userRepository.getByEmail(email);
            if (user == null) {
                throw new NotFoundException("There are not user whit this email!!!");
            }

            Authority authority = authorityRepository.getByName(Constants.MANAGER);
            authorityRepository.addManager(authority.getId(), user.getId());

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void deleteManager(String email) throws  NotFoundException, InternalErrorException {
        try {

            User user = userRepository.getByEmail(email);
            if (user == null) {
                throw new NotFoundException("There are not user whit this email!!!");
            }

            Authority authority = authorityRepository.getByName(Constants.MANAGER);
            authorityRepository.deleteManager(authority.getId(), user.getId());

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }


    @Override
    @Transactional
    public void blockUser(String email) throws NotFoundException, AccessDeniedException, InternalErrorException {
        try {
            User user = userRepository.getByEmail(email);
            if (user == null) {
                throw new NotFoundException("There are not user whit this email!!!");
            }

            if (user.getStatus().equals(Status.Deleted)) {
                throw new AccessDeniedException("This user already is deleted");
            }

            if (!user.getStatus().equals(Status.Blocked)) {
                user.setStatus(Status.Blocked);
                userRepository.save(user);
            } else if (user.getStatus().equals(Status.Blocked)) {
                user.setStatus(Status.Active);
                userRepository.save(user);
            }

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    public List<User> getAllManager() throws  InternalErrorException {
        try {


            return userRepository.getAllManager();

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    public List<User> getAllUser() throws InterruptedException, InternalErrorException {
        return null;
    }

    @Override
    public List<Transmitted> getAllTransmitted() throws InterruptedException, InternalErrorException {
        return null;
    }

    @Override
    public List<Transmitted> getAllPay() throws InterruptedException, InternalErrorException {
        return null;
    }
}
