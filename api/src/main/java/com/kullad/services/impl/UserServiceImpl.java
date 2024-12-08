package com.kullad.services.impl;

import com.kullad.exceptions.ResourceNotFoundException;
import com.kullad.models.User;
import com.kullad.repositories.UserRepository;
import com.kullad.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        log.info("Saving user with email: {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Finding user by email: {}", email);
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        log.info("Updating user with email: {}", user.getEmail());
        User currentUser = userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + user.getEmail()));

        currentUser.setName(user.getName());
        currentUser.setPassword(user.getPassword());
        return userRepository.save(currentUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
