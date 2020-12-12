package com.interview.assignment.vehiclemaintenance.resource;

import com.interview.assignment.vehiclemaintenance.model.StatusModel;
import com.interview.assignment.vehiclemaintenance.model.User;
import com.interview.assignment.vehiclemaintenance.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveUser() throws Exception {
        when(userService.saveUser(new User()))
                .thenReturn(new User());

        ResponseEntity<StatusModel> response = userResource.saveUser(new User());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void saveUser4XXStatus() throws Exception {
        when(userService.saveUser(new User()))
                .thenReturn(new User());

        ResponseEntity<StatusModel> response = userResource.saveUser(null);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserId(1l);
        when(userService.updateUser(user.getUserId(), user))
                .thenReturn(user);

        ResponseEntity<?> response = userResource.updateUser(user.getUserId(), user);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setUserId(1l);
        when(userService.getUser(any()))
                .thenReturn(Optional.of(user));

        ResponseEntity<?> response = userResource.getUser(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void searchUser() {
        User user = new User();
        user.setUserId(1l);
        user.setCellPhone("919900505288");

        when(userService.searchUser(any()))
                .thenReturn(user);

        ResponseEntity<?> response = userResource.searchUser("919900505288");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
