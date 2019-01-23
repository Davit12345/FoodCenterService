package com.example.foodcenter.controller;

import com.example.foodcenter.exceptions.AccessDeniedException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.User;
import com.example.foodcenter.model.enums.Status;
import com.example.foodcenter.repository.UserRepository;
import com.example.foodcenter.service.AdminService;
import com.example.foodcenter.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstants.Admin_CONTROLLER_URL)
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {


    @Autowired
    private AdminService adminService;


    @Autowired
    private UserRepository userRepository;


    @PutMapping
    public ResponseEntity addManager(@RequestParam String email) throws NotFoundException, InterruptedException, InternalErrorException {

        adminService.addManager(email);
        return ResponseEntity.ok("success add Manager");
    }


    @DeleteMapping
    public ResponseEntity deleteManage(@RequestParam String email) throws NotFoundException, InternalErrorException, InterruptedException {

        adminService.deleteManager(email);
        return ResponseEntity.ok("success delete Manager");
    }



    @PostMapping
    public ResponseEntity BlockONorOff(@RequestParam String email) throws InterruptedException, AccessDeniedException, NotFoundException, InternalErrorException {



        adminService.blockUser(email);

        User user = userRepository.getByEmail(email);
        if (user.getStatus().equals( Status.Blocked)) {
            return ResponseEntity.ok("success block On user");
        }
        return ResponseEntity.ok("success block Off  user");
    }


    @GetMapping
    public  ResponseEntity getAllManager(){

       List<User> managers= userRepository.getAllManager();
        return  ResponseEntity.ok().body(managers);
    }

}
