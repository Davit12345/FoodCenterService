package com.example.foodcenter.service.impl;

import com.example.foodcenter.model.Authority;
import com.example.foodcenter.model.User;
import com.example.foodcenter.model.enums.Status;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.repository.AuthorityRepository;
import com.example.foodcenter.repository.UserRepository;
import com.example.foodcenter.service.UserService;
import com.example.foodcenter.util.Constants;
import com.example.foodcenter.util.MailSenderClient;
import com.example.foodcenter.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private MailSenderClient mailClient;


    @Override
    @Transactional
    public void add(User user) throws DuplicateDataException, InternalErrorException {

        try {
            User duplicate = userRepository.getByEmail(user.getEmail());
            if (duplicate != null) {
                throw new DuplicateDataException("There is user with this email");
            }

            user.setStatus(Status.Unverified);
            user.setCode(Util.generateRandomChars());
            user.setPassword(Util.getEncoded(user.getPassword()));
            Authority authority = authorityRepository.getByName(Constants.ROLE_USER);
            userRepository.save(user);
            user.setAuthorities(Collections.singleton(authority));

            mailClient.send(user.getEmail(), Constants.EMAIL_SUBJECTS_VERIFICATION, Util.getVerificationMessage(user.getName(), user.getCode()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }


    }



    @Override
    @Transactional
    public void verify(String email, String verificationCode) throws InternalErrorException, TeapotException {
        try {
            User user = userRepository.getByEmail(email);
            if (user.getStatus().equals(Status.Active)) {
                throw new TeapotException("USer is already verified, Why do you want to verify him?");
            }
            if (verificationCode.equals(user.getCode())) {
                user.setCode(null);
                user.setStatus(Status.Active);
                userRepository.save(user);
            } else {

                throw new IllegalAccessError(Constants.WRONG_REQUEST);
            }
        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }


    @Override
    public void sendRecoveringCode(String email) throws InternalErrorException {

        try {
            User user = userRepository.getByEmail(email);
            if (user != null) {
                if (user.getStatus() == Status.Active) {
                    user.setCode(Util.generateRandomChars());
                    mailClient.send(user.getEmail(), Constants.EMAIL_SUBJECTS_RECOVERING, Util.getRecoveringMessage(user.getName(), user.getCode()));
                    userRepository.save(user);
                } else {
                    throw new IllegalAccessError("Pleas  pass the  verification");

                }

            } else {

                throw new IllegalAccessError(Constants.WRONG_REQUEST);
            }
        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }

    }


    @Transactional
    @Override
    public void changePassword(String email, String password, String recoveringCode) throws InternalErrorException {

        try {
            User user = userRepository.getByEmail(email);

            if (recoveringCode.equals(user.getCode())) {
                user.setPassword(Util.getEncoded(password));
                user.setCode(null);
                userRepository.save(user);
            } else {
                throw new IllegalAccessError(Constants.WRONG_REQUEST);
            }
        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }

    }


    @Transactional
    @Override
    public void resendVerificationCode(String email) throws NotFoundException, InternalErrorException {
        try {
            User user = userRepository.getByEmail(email);
            if (user == null) {
                throw new NotFoundException("There is no User with this email");
            }
            if (user.getStatus() != Status.Unverified) {
                throw new NotFoundException("Not Found");

            }
            user.setCode(Util.generateRandomChars());
            mailClient.send(user.getEmail(), Constants.EMAIL_SUBJECTS_VERIFICATION, Util.getVerificationMessage(user.getName(), user.getCode()));
        } catch (RuntimeException e) {
            throw new InternalErrorException("Something went wrong, please try later");
        }
    }


    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) {

        return userRepository.getByEmail(email);
    }
}
