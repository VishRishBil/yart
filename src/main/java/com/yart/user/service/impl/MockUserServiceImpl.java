package com.yart.user.service.impl;

import com.yart.common.bean.UserWrapper;
import com.yart.common.bean.UserWrapper.STATUS_CODES;
import com.yart.common.exception.YartServiceException;
import com.yart.user.bean.User;
import com.yart.user.service.UserService;


public class MockUserServiceImpl implements UserService {

    @Override
    public UserWrapper createUser(User user) throws YartServiceException {
        String email = user.getEmail();
        String userId = user.getUserId();
        if(email == null || userId == null){
            throw new YartServiceException("Mandatory Fields Empty");
        }
        UserWrapper result = new UserWrapper();
        if(doesEmailExist(email)){
            result.setStatusCode(STATUS_CODES.EMAIL_EXISTS);
        } else if(doesUserIdExist(userId)){
            result.setStatusCode(STATUS_CODES.USERID_EXISTS);
        } else {
            result.setStatusCode(STATUS_CODES.CREATE_OK);
            result.setUser(user);
        }
        return result;
    }

    @Override
    public UserWrapper modifyUser(User user) throws YartServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean removeUser(String userId) throws YartServiceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deactivateUser(String userId) throws YartServiceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean activateUser(String userId) throws YartServiceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User getUserById(String userId) throws YartServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * User ids starting with existent_ exists
     */
    @Override
    public boolean doesUserIdExist(String userId) throws YartServiceException {
        return userId.startsWith("existent_");
    }

    /**
     * Emails with domain Gmail exists
     */
    @Override
    public boolean doesEmailExist(String email) throws YartServiceException {
        return email.endsWith("@gmail.com");
    }

    @Override
    public UserWrapper modifyPassword(User user, String oldPassword, String newPassword) throws YartServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean modifyForgottenPassword(String newPassword) throws YartServiceException {
        // TODO Auto-generated method stub
        return false;
    }

}
