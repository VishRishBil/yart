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
        UserWrapper result = new UserWrapper();
        if(doesUserIdExist(user.getUserId())){
            result.setStatusCode(STATUS_CODES.MODIFY_OK);
            result.setUser(user);
        } else {
            result.setStatusCode(STATUS_CODES.OPERATION_FAILED);
        }
        return result;
    }

    @Override
    public boolean removeUser(String userId) throws YartServiceException {
        if(doesUserIdExist(userId)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deactivateUser(String userId) throws YartServiceException {
        if(doesUserIdExist(userId)){
            return true;
        }
        return false;
    }

    @Override
    public boolean activateUser(String userId) throws YartServiceException {
        if(doesUserIdExist(userId)){
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(String userId) throws YartServiceException {
        return getUserById(userId, false);
    }
    public User getUserById(String userId, boolean needPassword) throws YartServiceException {
        if(doesUserIdExist(userId)){
            User user = new User();
            user.setUserId(userId);
            user.setEmail(userId.split("_")[1]+"@gmail.com");
            user.setPassword(needPassword ? "password" : null);
            user.setActive(userId.contains("_active"));
                            
            return user;
        }
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
    public boolean modifyForgottenPassword(User user, String newPassword) throws YartServiceException {
        if(doesUserIdExist(user.getUserId())){
            return true;
        }
        return false;
    }

    @Override
    public UserWrapper verifyCredentials(User user) throws YartServiceException {
        String password = user.getPassword();
        String userId = user.getUserId();
        if(userId==null || password == null){
            throw new YartServiceException("UserId or Password is null");
        }
        User userFromDB = getUserById(userId, true);
        UserWrapper result = new UserWrapper();
        if(userFromDB != null && password.equals(userFromDB.getPassword())){
            userFromDB.setPassword(null);
            result.setUser(user);
            result.setStatusCode(userFromDB.isActive() ? 
                    STATUS_CODES.VALID_CREDENTIALS : STATUS_CODES.USER_INACTIVE);
        } else {
            result.setStatusCode(STATUS_CODES.INVALID_CREDENTIALS);
        }
        return result;
    }

    @Override
    public UserWrapper modifyPassword(User user, String newPassword) throws YartServiceException {
        UserWrapper result = new UserWrapper();
        if(doesUserIdExist(user.getUserId())){
            result.setStatusCode(STATUS_CODES.MODIFY_OK);
            user.setPassword(null);
            result.setUser(user);
        } else {
            result.setStatusCode(STATUS_CODES.OPERATION_FAILED);
        }
        return result;
    }

   

}
