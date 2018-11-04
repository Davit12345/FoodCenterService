package com.example.foodcenter.repository;

import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.OrderItem;
import com.example.foodcenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    OrderItem getByUserAndMenu(User user, Menu menu);


    List<OrderItem> getByUser(User user);


    void deleteAllByUser(User user);
}
