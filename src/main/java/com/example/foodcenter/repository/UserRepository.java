package com.example.foodcenter.repository;

import com.example.foodcenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User getByEmail(String email);



    @Query(nativeQuery = true,value = "SELECT * FROM user WHERE id IN " +
            "(SELECT  user_authority.user_id FROM user_authority where authority_id IN" +
            "(SELECT  id FROM authority WHERE  name='MANAGER')) ")
    List<User> getAllManager();
}
