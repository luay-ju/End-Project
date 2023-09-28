package com.luluShop.customerservice.login.service;

import com.luluShop.customerservice.login.entity.User;
import com.luluShop.customerservice.login.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        userService.registerUser(user);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testLogin() {
        String email = "user@example.com";
        String password = "password";
        User expectedUser = new User();
        when(userRepo.findByEmailAndPassword(email, password)).thenReturn(expectedUser);

        User result = userService.login(email, password);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetAllUsersWithKeyword() {
        String keyword = "searchKeyword";
        User user1 = new User();
        User user2 = new User();
        when(userRepo.search(keyword)).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.getAllUsers(keyword);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllUsersWithoutKeyword() {
        User user1 = new User();
        User user2 = new User();
        when(userRepo.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.getAllUsers(null);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserById() {
        Integer userId = 1;
        User expectedUser = new User();
        when(userRepo.findById(userId)).thenReturn(Optional.of(expectedUser));

        User result = userService.getUserById(userId);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetUserByIdNotFound() {
        Integer userId = 1;
        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
    }

    @Test
    public void testDeleteUserById() {
        Integer userId = 1;
        userService.deleteUserBYId(userId);
        verify(userRepo, times(1)).deleteById(userId);
    }

    // ____


    @Test
    public void testRegisterUserInvalidUser() {

        User invalidUser = new User();


        doThrow(RuntimeException.class).when(userRepo).save(invalidUser);


        assertThrows(RuntimeException.class, () -> userService.registerUser(invalidUser));


        verify(userRepo, times(1)).save(invalidUser);
    }


    @Test
    public void testLoginWithInvalidCredentials() {
        String email = "user@example.com";
        String password = "password";
        when(userRepo.findByEmailAndPassword(email, password)).thenReturn(null);

        User result = userService.login(email, password);

        assertNull(result);
    }

    @Test
    public void testGetAllUsersEmptyResult() {
        when(userRepo.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.getAllUsers(null);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteUserByIdNotFound() {
        Integer userId = 1;
        doThrow(EmptyResultDataAccessException.class).when(userRepo).deleteById(userId);

        assertThrows(RuntimeException.class, () -> userService.deleteUserBYId(userId));
    }

}
