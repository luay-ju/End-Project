package com.luluShop.customerservice.login.service;


import com.luluShop.customerservice.login.entity.User;
import com.luluShop.customerservice.login.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repo;

    @Override
    public void registerUser(User user) {
        repo.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = repo.findByEmailAndPassword(email,password);
        return user;
    }


    @Override
    public List<User> getAllUsers(String keyword) {

        if (keyword != null){
            return repo.search(keyword);
        }
        return (List<User>) repo.findAll();
    }

    @Override
    public User getUserById(Integer userId) {
        Optional<User> optional = repo.findById(userId);
        User product = null;
        if (optional.isPresent()){
            product = optional.get();
        }else {
            throw new RuntimeException(" User not found for id :: " + userId);
        }
        return product;
    }

    @Override
    public void deleteUserBYId(Integer userId) {
        this.repo.deleteById(userId);
    }

}
