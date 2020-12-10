package com.interview.assignment.vehiclemaintenance.service;

import com.interview.assignment.vehiclemaintenance.model.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUser(Long userId);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    User searchUser(String cellPhone);
}
