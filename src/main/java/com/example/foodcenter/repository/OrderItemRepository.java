package com.example.foodcenter.repository;

import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    OrderItem getByUserAndAndMenu(User user, Menu menu);
}
