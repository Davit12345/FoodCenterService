package com.example.foodcenter.service.impl;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.User;
import com.example.foodcenter.model.enums.MenuItems;
import com.example.foodcenter.repository.MenuRepository;
import com.example.foodcenter.service.MenuService;
import com.example.foodcenter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuRepository menuRepository;


    @Override
    @Transactional
    public void addMenu(Menu menu) throws InternalErrorException, DuplicateDataException {
        try {
            Menu duplicate = menuRepository.getByName(menu.getName());
            if (duplicate != null) {
                throw new DuplicateDataException("There is Menu name with this name!");
            }
            menuRepository.save(menu);

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteMenu(String name) {

    }

    @Override
    public Menu getMenuByName(String name) {

        return menuRepository.getByName(name);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getByMenuItem(String item) throws InternalErrorException {
        try {
        MenuItems menuItems=MenuItems.valueOf(item);

            return menuRepository.getByMenuItem(menuItems);

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> getAllMenu() throws InternalErrorException {
        try {

            return menuRepository.findAll();

        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }


}
