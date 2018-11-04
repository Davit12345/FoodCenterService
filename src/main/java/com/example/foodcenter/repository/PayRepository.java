package com.example.foodcenter.repository;

import com.example.foodcenter.model.Pay;
import com.example.foodcenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay,Integer> {


    Pay getByUser(User user);
}
