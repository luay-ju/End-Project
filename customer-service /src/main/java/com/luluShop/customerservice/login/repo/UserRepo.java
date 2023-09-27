package com.luluShop.customerservice.login.repo;


import com.luluShop.customerservice.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String Password);

    //User findById(Integer userId);

    @Query("SELECT users FROM User users WHERE CONCAT(users.id, ' ', users.name, ' ', users.email)  LIKE %?1%")
    List<User> search(String keyword);

}
