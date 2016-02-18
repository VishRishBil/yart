package com.yart.user.service.impl;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.yart.common.bean.UserWrapper;
import com.yart.common.exception.YartServiceException;
import com.yart.user.bean.User;
import com.yart.user.service.UserService;
import com.yart.common.bean.UserWrapper.STATUS_CODES;

public class MockUserServiceTest {
    
    UserService userService;
    
    @Before
    public void init(){
        userService = new MockUserServiceImpl();
    }

    @Test
    public void testCreateUser() {
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setUserId("nonexistent_1");
        
        User user2 = new User();
        user2.setEmail("xyz@yahoo.com");
        user2.setUserId("existent_");
        
        User user3 = new User();
        user3.setEmail("abc@xyz.com");
        user3.setUserId("not_exists");
        
        try {
            UserWrapper result1 = userService.createUser(user1);
            UserWrapper result2 = userService.createUser(user2);
            UserWrapper result3 = userService.createUser(user3);
            assertEquals(STATUS_CODES.EMAIL_EXISTS, result1.getStatusCode());
            assertEquals(STATUS_CODES.USERID_EXISTS, result2.getStatusCode());
            assertEquals(STATUS_CODES.CREATE_OK, result3.getStatusCode());
            assertNull(result3.getUser().getPassword());
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModifyUser() {
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        user1.setUserId("existent_1");
        try {
            UserWrapper result1 = userService.modifyUser(user1);
            assertEquals(result1.getStatusCode(), STATUS_CODES.MODIFY_OK);
            assertNull(result1.getUser().getPassword());

        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveUser() {
        try {
            boolean result = userService.removeUser("existent_1");
            assertTrue(result);
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeactivateUser() {
        try {
            boolean result = userService.deactivateUser("existent_2");
            assertTrue(result);
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testActivateUser() {
        try {
            boolean result = userService.deactivateUser("existent_2");
            assertTrue(result);
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserById() {
        try {
            User user1 = userService.getUserById("existent_abc");
            User user2 = userService.getUserById("nonexistent_mno");
            assertEquals("abc@gmail.com", user1.getEmail());
            assertNull(user1.getPassword());
            assertNull(user2);
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoesUserIdExist() {
        try {
            assertTrue(userService.doesUserIdExist("existent_1"));
            assertFalse(userService.doesUserIdExist("nonexistent_1"));
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoesEmailExist() {
        try {
            assertTrue(userService.doesEmailExist("abc@gmail.com"));
            assertFalse(userService.doesEmailExist("abc@xyz.com"));
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModifyPassword() {
        User user1 = new User();
        user1.setUserId("existent_1");
        user1.setPassword("oldPassword");
        User user2 = new User();
        user2.setUserId("nonexistent_1");
        user2.setPassword("oldPassword");
        try {
            UserWrapper result1 = userService.modifyPassword(user1, "newPassword");
            UserWrapper result2 = userService.modifyPassword(user2, "newPassword");
            assertEquals(STATUS_CODES.MODIFY_OK, result1.getStatusCode());
            assertNull(result1.getUser().getPassword());
            assertEquals(STATUS_CODES.OPERATION_FAILED, result2.getStatusCode());
        } catch(YartServiceException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testModifyForgottenPassword() {
        User user1 = new User();
        user1.setUserId("existent_1");
        User user2 = new User();
        user2.setUserId("nonexistent_1");
        try {
            assertTrue(userService.modifyForgottenPassword(user1, "newPassword"));
            assertFalse(userService.modifyForgottenPassword(user2, "newPassword"));
        } catch(YartServiceException e){
            e.printStackTrace();
        }
    }
    
    @Test
    public void testVerifyCredentials(){
        User user1 = new User();
        user1.setUserId("existent_active");
        user1.setPassword("wrongpassword");
        User user2 = new User();
        user2.setUserId("existent_inactive");
        user2.setPassword("password");
        User user3 = new User();
        user3.setUserId("nonexistent_1");
        user3.setPassword("password");
        try{
            UserWrapper result1 = userService.verifyCredentials(user1);
            assertEquals(STATUS_CODES.INVALID_CREDENTIALS, result1.getStatusCode());
            user1.setPassword("password");
            result1 = userService.verifyCredentials(user1);
            assertEquals(STATUS_CODES.VALID_CREDENTIALS, result1.getStatusCode());
            UserWrapper result2 = userService.verifyCredentials(user2);
            assertEquals(STATUS_CODES.USER_INACTIVE, result2.getStatusCode());
            UserWrapper result3 = userService.verifyCredentials(user3);
            assertEquals(STATUS_CODES.INVALID_CREDENTIALS, result3.getStatusCode());
        } catch(YartServiceException e){
            e.printStackTrace();
        }
    }

}
