package com.interview.assignment.vehiclemaintenance.service.impl;

import com.interview.assignment.vehiclemaintenance.exception.DuplicateException;
import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveUser() {
        User userExpected = new User();
        userExpected.setCellPhone("919900505288");
        when(userRepository.findByCellPhone(userExpected.getCellPhone())).thenReturn(null);
        when(userRepository.save(userExpected)).thenReturn(userExpected);
        User user = userService.saveUser(userExpected);
        assertEquals(userExpected.getCellPhone(), user.getCellPhone());

    }

    @Test(expected = DuplicateException.class)
    public void saveUserDuplicateCheck() {
        User userExpected = new User();
        userExpected.setCellPhone("919900505288");
        when(userRepository.findByCellPhone(userExpected.getCellPhone())).thenReturn(userExpected);
        userService.saveUser(userExpected);

    }

    @Test
    public void getUser() {
        User userExpected = new User();
        userExpected.setUserId(1l);
        when(userRepository.findById(userExpected.getUserId())).thenReturn(Optional.of(userExpected));
        Optional<User> optionalUser = userService.getUser(userExpected.getUserId());
        assertEquals(userExpected.getUserId(), optionalUser.get().getUserId());
    }

    @Test
    public void searchUser() {
        User userExpected = new User();
        userExpected.setCellPhone("919900505288");
        when(userRepository.findByCellPhone(userExpected.getCellPhone())).thenReturn(userExpected);
        User user = userService.searchUser(userExpected.getCellPhone());
        assertEquals(userExpected.getCellPhone(), user.getCellPhone());
    }

    @Test
    public void updateUser() {
        User userExpected = new User();
        userExpected.setUserId(1l);
        userExpected.setCellPhone("919900505288");
        when(userRepository.findById(1l)).thenReturn(Optional.of(userExpected));
        when(userRepository.save(userExpected)).thenReturn(userExpected);
        User user = userService.updateUser(userExpected.getUserId(), userExpected);
        assertEquals(userExpected.getCellPhone(), user.getCellPhone());
    }


}
