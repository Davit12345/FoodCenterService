package com.example.foodcenter.service;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.enums.MenuItems;

import java.util.List;

public interface MenuService {
    void addMenu(Menu menu) throws InternalErrorException, DuplicateDataException;

    void deleteMenu(String name);

    Menu getMenuByName(String name);

    List<Menu> getByMenuItem(String item) throws InternalErrorException;

    List<Menu> getAllMenu() throws InternalErrorException;

}
