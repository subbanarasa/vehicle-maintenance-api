package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.service.UserService;
import com.interview.assignment.vehiclemaintenance.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserResource {

    private static Logger logger = LoggerFactory.getLogger(UserResource.class);

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<StatusModel> saveUser(@RequestBody User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("Save user request:" + user);
        }
        User userSaved = userService.saveUser(user);
        if (userSaved == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, userSaved), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (logger.isDebugEnabled()) {
            logger.debug("update user for id:" + id + "|User" + user);
        }
        User userSaved = userService.updateUser(id, user);
        if (userSaved == null) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, userSaved), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get user for id:" + id);
        }
        Optional<User> optionalUser = userService.getUser(id);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(new StatusModel(Constants.FAILED, Constants.FAILED_MSG, null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, optionalUser.get()), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Delete user for id:" + id);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(new StatusModel(Constants.SUCCESS, Constants.SUCCESS_MSG, null), HttpStatus.OK);
    }

}
