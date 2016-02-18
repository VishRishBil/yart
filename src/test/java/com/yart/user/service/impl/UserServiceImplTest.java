package com.yart.user.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yart.YartApplication;
import com.yart.common.exception.YartServiceException;
import com.yart.user.bean.User;
import com.yart.user.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(YartApplication.class)
@WebIntegrationTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    
    @Before
    public void init(){
        
        
    }
    @Test
    public void testCreateUser() {
        try {
            User user = new User();
            user.setActive(true);
            user.setEmail("abc@gmail.com");
            user.setUserId("active_userabc");
            user.setFirstName("ABC");
            user.setLocale("en_US");
            user.setPassword("password");
            userService.createUser(user);
            assertTrue(userService.doesEmailExist("abc@gmail.com"));
            assertTrue(userService.doesUserIdExist("active_userabc"));
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModifyUser() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveUser() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeactivateUser() {
        fail("Not yet implemented");
    }

    @Test
    public void testActivateUser() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetUserById() {
        fail("Not yet implemented");
    }

    @Test
    public void testDoesUserIdExist() {
        fail("Not yet implemented");
    }

    @Test
    public void testDoesEmailExist() {
        fail("Not yet implemented");
    }

    @Test
    public void testModifyPassword() {
        fail("Not yet implemented");
    }

    @Test
    public void testModifyForgottenPassword() {
        fail("Not yet implemented");
    }

}
