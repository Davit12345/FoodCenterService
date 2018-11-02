package com.example.foodcenter.repository;

import com.example.foodcenter.model.Menu;
import com.example.foodcenter.model.enums.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository  extends JpaRepository<Menu,Integer> {

    List<Menu> getByMenuItem(MenuItems item);

    Menu   getByName(String mame);




}
