package com.example.foodcenter.controller;

import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Authority;
import com.example.foodcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity getUserChoose(Principal principal) throws InternalErrorException {

        List<Authority> roles=userService.getRoles(principal.getName());

        return ResponseEntity.ok().body(roles);
    }

}
