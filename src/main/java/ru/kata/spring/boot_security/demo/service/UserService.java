package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String username);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getById(int id);

    void deleteUserById(int id);
}
