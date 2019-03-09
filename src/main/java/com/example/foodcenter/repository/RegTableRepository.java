package com.example.foodcenter.repository;

import com.example.foodcenter.model.RegTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegTableRepository extends JpaRepository<RegTable,Integer> {

}
