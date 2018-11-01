package com.example.foodcenter.service;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Menu;

public interface MenuService {
    void addMenu(Menu menu) throws InternalErrorException, DuplicateDataException;

    void deleteMenu(String name);

    Menu getMenuByName();



}
