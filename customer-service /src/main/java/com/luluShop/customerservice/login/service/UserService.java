package com.luluShop.customerservice.login.service;


import com.luluShop.customerservice.login.entity.User;

import java.util.List;

// this able to store the data in the db
public interface UserService {

    public void registerUser(User user);

    public User login(String email, String password);

    public List<User> getAllUsers(String keyword);

    public User getUserById(Integer userId);

    public void deleteUserBYId(Integer productId);

}
