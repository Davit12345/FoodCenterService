package com.example.foodcenter.controller;

import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.service.AdminService;
import com.example.foodcenter.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlConstants.Admin_CONTROLLER_URL)
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {


    @Autowired
    private AdminService adminService;


    @PutMapping
    public ResponseEntity addManager(@RequestParam String email) throws NotFoundException, InterruptedException {

        adminService.addManager(email);
        return  ResponseEntity.ok("success add Manager");
    }

    @PostMapping
    public  ResponseEntity deleteManage(@RequestParam String email) throws NotFoundException, InterruptedException {

        adminService.deleteManager(email);
        return  ResponseEntity.ok("success delete Manager");
    }

}
