package com.example.foodcenter.repository;



import com.example.foodcenter.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    Authority getByName(String name);

    @Modifying
    @Query(nativeQuery = true,value = "INSERT INTO user_authority(authority_id,user_id) VALUES (:authID,:userID)" )
    void addManager(@Param("authID") int authorityId, @Param("userID") int userId);



    @Modifying
    @Query(nativeQuery = true,value = "DELETE FROM user_authority WHERE user_id=:userID and authority_id=:authID" )
    void deleteManager(@Param("authID") int authorityId, @Param("userID") int userId);






}
