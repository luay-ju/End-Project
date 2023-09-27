package com.luluShop.customerservice.login.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.luluShop.customerservice.login.entity.User;
import com.luluShop.customerservice.login.repo.UserRepo;
import com.luluShop.customerservice.login.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getTheUser() {
    }

    @Test
    void login() {
        String result = controller.login(model);
        assertEquals("login", result);
    }

    @Test
    void showLoginPage() {
        String result = controller.showLoginPage();
        assertEquals("login", result);
    }

    @Test
    void showHomePage() {
        String result = controller.showHomePage();
        assertEquals("home", result);
    }

    @Test
    void register() {
        String result = controller.register(model);
        assertEquals("register", result);
    }

    @Test
    void userLogin() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepo.findByEmailAndPassword("user@example.com", "password")).thenReturn(user);

        String result = controller.userLogin(user);

        assertEquals("loginSuccess", result);
    }

    @Test
    void registerUser() {
        User user = new User();
        user.setPassword("password");
        user.setCpassword("password");

        String result = controller.registerUser(user);

        assertEquals("loginSuccess", result);
    }

    @Test
    void editUser() {
        // Mock data for the test
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userService.getAllUsers(anyString())).thenReturn(userList);

        String result = controller.editUser(model, "keyword");

        assertEquals("Edit-Users", result);
        verify(model).addAttribute(eq("listUsers"), anyList());
        verify(model).addAttribute(eq("keyword"), eq("keyword"));
    }

    @Test
    void deleteProduct() {
        String result = controller.deleteUser(1); // Assuming ID 1 for the test
        assertEquals("redirect:http://localhost:8081/showEditUsers", result);
        verify(userService).deleteUserBYId(1); // Verify that the deleteUserBYId method is called with the correct ID
    }

    @Test
    void updateUser() {
        // Mock data for the test
        User user = new User();
        user.setId(1); // Assuming ID 1 for the test
        when(userService.getUserById(1)).thenReturn(user);

        String result = controller.updateUser(1, model);

        assertEquals("", result); // You might want to specify a return value here based on your application logic
        verify(model).addAttribute(eq("product"), eq(user));
    }
}
