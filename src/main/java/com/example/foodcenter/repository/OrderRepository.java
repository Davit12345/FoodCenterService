package com.example.foodcenter.repository;

import com.example.foodcenter.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    @Modifying
    @Query(nativeQuery = true,value ="select * from orders where orders.send=false")
            List<Orders> getAllAnSendOrders();

}
