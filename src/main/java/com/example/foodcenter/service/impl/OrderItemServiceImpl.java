package com.example.foodcenter.service.impl;

import com.example.foodcenter.controller.forJsonModels.Item;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.exceptions.TeapotException;
import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;
import com.example.foodcenter.repository.OrderItemRepository;
import com.example.foodcenter.service.MenuService;
import com.example.foodcenter.service.OrderItemService;
import com.example.foodcenter.service.UserService;
import com.example.foodcenter.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;


    @Override
    @Transactional
    public void addOrderItem(String email, Item item) throws InternalErrorException, TeapotException {

        try {
            User user = userService.getByEmail(email);
            Menu menu = menuService.getMenuByName(item.getName());
            if(menu==null||user==null){
                throw  new TeapotException("Please don't kill me");
            }

            if (orderItemRepository.getByUserAndMenu(user, menu) != null) {

                OrderItem orderItem = orderItemRepository.getByUserAndMenu(user, menu);
                orderItem.setQuantity(orderItem.getQuantity() + item.getQuantity());
                orderItemRepository.save(orderItem);
            } else {

                OrderItem orderItem = new OrderItem();

                orderItem.setQuantity(item.getQuantity());
                orderItem.setMenu(menu);
                orderItem.setUser(user);
                orderItemRepository.save(orderItem);
            }


        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getAllItemOneCostumer(String email) throws InternalErrorException {
        try{

        return orderItemRepository.getByUser(userService.getByEmail(email));


        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }

    @Override
    public void deleteOneProduct(User user, String name, OrderItem orderItem) {

    }

    @Override
    public void updateOneProduct(User user, String name, OrderItem orderItem) {

    }
}
