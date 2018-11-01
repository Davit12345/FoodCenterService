package com.example.foodcenter.service.impl;

import com.example.foodcenter.exceptions.InternalErrorException;
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

import java.beans.Transient;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void addOrderItem(String email, String name, int quantity) throws InternalErrorException {

        try {
            User user = userService.getByEmail(email);
            Menu menu = menuService.getMenuByName(name);

            if (orderItemRepository.getByUserAndAndMenu(user, menu) != null) {

                OrderItem orderItem = orderItemRepository.getByUserAndAndMenu(user, menu);
                orderItem.setQuantity(orderItem.getQuantity() + quantity);

            } else {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(quantity);
                orderItem.setUser(user);
                orderItem.setMenu(menu);

                orderItemRepository.save(orderItem);
            }
        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }

    }

    @Override
    public List<OrderItem> getAllItemOneCostumer(User user) {
        return null;
    }

    @Override
    public void deleteOneProduct(User user, String name, OrderItem orderItem) {

    }

    @Override
    public void updateOneProduct(User user, String name, OrderItem orderItem) {

    }
}
