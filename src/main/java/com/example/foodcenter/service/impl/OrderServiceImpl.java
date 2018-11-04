package com.example.foodcenter.service.impl;

import com.example.foodcenter.exceptions.BadRequestException;
import com.example.foodcenter.exceptions.InternalErrorException;
import com.example.foodcenter.model.Orders;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;
import com.example.foodcenter.repository.OrderItemRepository;
import com.example.foodcenter.repository.OrderRepository;
import com.example.foodcenter.repository.UserRepository;
import com.example.foodcenter.service.MenuService;
import com.example.foodcenter.service.OrderService;
import com.example.foodcenter.util.Constants;
import org.hibernate.criterion.NotNullExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuService menuService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
    public void addOrder(String email) throws InternalErrorException, BadRequestException {
        try {
            // add in db ->Orders

            User user = userRepository.getByEmail(email);

            List<OrderItem> orderItemList = orderItemRepository.getByUser(user);

            if(orderItemList==null){
                throw  new BadRequestException("Pleas choose any  product ");
            }
            for (OrderItem orderItem : orderItemList) {

                Orders order = new Orders();
                order.setMenu(orderItem.getMenu());
                order.setQuantity(orderItem.getQuantity());
                order.setUser(orderItem.getUser());
                order.setTotalPrice(orderItem.getQuantity() * orderItem.getMenu().getPrice());


                orderRepository.save(order);
            }


            //delete in db->OrderItem
            orderItemRepository.deleteAllByUser(user);


        } catch (RuntimeException e) {
            throw new InternalErrorException(Constants.ERROR_MESSAGE);
        }
    }
}
