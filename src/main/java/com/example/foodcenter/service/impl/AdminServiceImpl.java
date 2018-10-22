package com.example.foodcenter.service.impl;


import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.Authority;
import com.example.foodcenter.model.User;
import com.example.foodcenter.repository.AuthorityRepository;
import com.example.foodcenter.repository.UserRepository;
import com.example.foodcenter.service.AdminService;
import com.example.foodcenter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void addManager(String email) throws InterruptedException, NotFoundException {
        try {
            User user = userRepository.getByEmail(email);
        if(user==null){
            throw  new NotFoundException("There are not user whit this email!!!");
        }

            Authority authority = authorityRepository.getByName(Constants.MANAGER);
                authorityRepository.addAuthority(authority.getId(),user.getId());

        } catch (RuntimeException e) {
            throw new InterruptedException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteManager(String email) {

    }


    @Override
    public void blockUser(User user) {

    }

    @Override
    public List<User> getAllManager() {
        return null;
    }
}
