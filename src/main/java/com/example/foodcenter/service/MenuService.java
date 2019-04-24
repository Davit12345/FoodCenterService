package com.example.foodcenter.service;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.enums.MenuItems;

import java.util.List;

public interface MenuService {

    //admin
    void addMenu(Menu menu) throws InternalErrorException, DuplicateDataException;
    void deleteMenu(int id) throws InternalErrorException;
    //user
    Menu getMenuByName(String name);
    Menu getMenuById(int id);
    List<Menu> getByMenuItem(String item) throws InternalErrorException;
    List<Menu> getAllMenu() throws InternalErrorException;


}
