package com.kullad.services;

import com.kullad.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
