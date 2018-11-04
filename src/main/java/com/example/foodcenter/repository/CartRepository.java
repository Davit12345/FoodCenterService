package com.example.foodcenter.repository;

import com.example.foodcenter.model.Cart;
import com.example.foodcenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    List<Cart> getCartByUser(User user);

    Cart getCartByCartCode(String code);

}
