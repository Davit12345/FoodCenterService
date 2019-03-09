package com.example.foodcenter.controller;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.NotFoundException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.service.MenuService;
import com.example.foodcenter.util.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlConstants.MENU_CONTROLLER_URL)
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuService menuService;


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity addMenu(@Valid @RequestBody Menu menu) throws InternalErrorException, DuplicateDataException {

        menuService.addMenu(menu);

        return ResponseEntity.ok("success add one Menu column");

    }

    @GetMapping("")
    public ResponseEntity getMenu() throws InternalErrorException {

         List<Menu> menuList=menuService.getAllMenu();

        return ResponseEntity.ok().body(menuList);

    }


    @GetMapping("/{Item}")
    public ResponseEntity getMenuByItem(@PathVariable(value = "Item") String item ) throws InternalErrorException {



        List<Menu> menuList=menuService.getByMenuItem(item);

        return ResponseEntity.ok().body(menuList);

    }
    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteMenu(@PathVariable int id) throws InternalErrorException {

        menuService.deleteMenu(id);

        return ResponseEntity.ok("Success deleted Menu Item");
    }

}
