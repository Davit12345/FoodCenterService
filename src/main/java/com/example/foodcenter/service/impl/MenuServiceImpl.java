package com.example.foodcenter.service.impl;

import com.example.foodcenter.exceptions.DuplicateDataException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.User;
import com.example.foodcenter.repository.MenuRepository;
import com.example.foodcenter.service.MenuService;
import com.example.foodcenter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
     private MenuRepository menuRepository;



    @Transactional
    @Override
    public void addMenu(Menu menu) throws InternalErrorException, DuplicateDataException {
        try{
            Menu duplicate = menuRepository.getByName(menu.getName());
            if (duplicate != null) {
                throw new DuplicateDataException("There is Menu name with this name!");
            }
            menuRepository.save(menu);

        }catch (RuntimeException e){
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteMenu(String name) {

    }

    @Override
    public Menu getMenuByName() {
        return null;
    }
}
