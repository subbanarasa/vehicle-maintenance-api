package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.exception.DuplicateException;
import com.interview.assignment.vehiclemaintenance.exception.ResourceNotFoundException;
import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.repository.UserRepository;
import com.interview.assignment.vehiclemaintenance.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.findByCellPhone(user.getCellPhone());
        if (savedUser != null) {
            throw new DuplicateException("Duplicate user with Cellphone:" + user.getCellPhone());
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User searchUser(String cellPhone) {
        User user = userRepository.findByCellPhone(cellPhone);
        ;
        if (user == null) {
            throw new ResourceNotFoundException("User is not found for the cellPhone" + cellPhone);
        }
        return user;
    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User userTmp = optionalUser.get();
            if (StringUtils.hasLength(user.getFirstName())) {
                userTmp.setFirstName(user.getFirstName());
            }

            if (StringUtils.hasLength(user.getLastName())) {
                userTmp.setLastName(user.getLastName());
            }

            if (StringUtils.hasLength(user.getEmail())) {
                userTmp.setEmail(user.getEmail());
            }
            return userRepository.save(userTmp);
        }

        return null;

    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new ResourceNotFoundException("User ID " + userId + " not found");
        }
    }
}
