package com.example.foodcenter.controller;


import com.example.foodcenter.controller.forJsonModels.EmailAndCode;
import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.User;
import com.example.foodcenter.service.UserService;
import com.example.foodcenter.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlConstants.USERS_CONTROLLER_URL)
@CrossOrigin

public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity show() {

        return ResponseEntity.ok().body("success passed registry");

    }


    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody User user) throws DuplicateDataException, InternalErrorException {

        userService.addUser(user);
        return ResponseEntity.ok().body("success passed registry");

    }


    @PostMapping(UrlConstants.USERS_CONTROLLER_URL_VERIFY)
    public ResponseEntity verify(@Valid @RequestBody EmailAndCode emailAndCode) throws TeapotException, InternalErrorException {


        userService.verify(emailAndCode);


        return ResponseEntity.ok().body("you  success passed  the verification");
    }


    @PostMapping(UrlConstants.USERS_CONTROLLER_SEND_RECOVERING_CODE)
    public ResponseEntity sendRecoveringCode(@RequestParam String email) throws InternalErrorException {


        userService.sendRecoveringCode(email);
        return ResponseEntity.ok().body("See Your Recovering Code in your  Email");


    }


    @PostMapping(UrlConstants.USERS_CONTROLLER_CHANGE_PASSWORD)
    public ResponseEntity ChangePassword(@RequestParam String email, @RequestParam String password, @RequestParam String code) throws InternalErrorException {


        userService.changePassword(email, password, code);
        return ResponseEntity.ok().body("you  successful chane your password");


    }


    @PostMapping(UrlConstants.USERS_CONTROLLER_RESEND_RECOVERING_CODE)
    public ResponseEntity resendVerificationCode(@RequestParam String email) throws NotFoundException, InternalErrorException {
        userService.resendVerificationCode(email);
        return ResponseEntity.ok().build();
    }

}