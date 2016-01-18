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
        
        User user2 = new User();
        user2.setUserId("exists");
        
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
        } catch (YartServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModifyUser() {
    	User user1 = new User();
        user1.setEmail("abc@gmail.com");
        try {
        	UserWrapper result1 = userService.modifyUser(user1);
        	assertNull(result1.getUser().getPassword());
        	assertEquals(result1.getStatusCode(), STATUS_CODES.MODIFY_OK);
        	
        }catch (YartServiceException e){
        	e.printStackTrace();
        }
    }

    @Test
    public void testRemoveUser() {
    	try{
    		boolean result = userService.removeUser("abc@gmail.com");
    		assertTrue(result);
    	} catch( YartServiceException e){
    		e.printStackTrace();
    	}
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
        fail("Not yet implemented");
    }

    @Test
    public void testModifyForgottenPassword() {
        fail("Not yet implemented");
    }

}
