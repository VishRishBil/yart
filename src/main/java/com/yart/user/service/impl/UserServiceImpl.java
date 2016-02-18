package com.yart.user.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yart.common.bean.UserWrapper;
import com.yart.common.bean.UserWrapper.STATUS_CODES;
import com.yart.common.exception.InvalidValueForFieldException;
import com.yart.common.exception.YartServiceException;
import com.yart.user.bean.User;
import com.yart.user.repository.UserRepository;
import com.yart.user.service.UserService;
import com.yart.util.CryptUtil;
import com.yart.util.FormatUtils;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);
    
    //private UserDao userDao;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserWrapper createUser(User user) throws YartServiceException {
        String email = user.getEmail();
        String userId = user.getUserId();
        logger.info(String.format("Creating user with id : %s and email : %s", userId, email));
        if(email == null || userId == null){
            throw new YartServiceException("Mandatory Fields Empty");
        }
        validateFields(user);
        
        UserWrapper result = new UserWrapper();
        if(doesEmailExist(email)){
            logger.warn(String.format("Email %s already exists in system", email));
            result.setStatusCode(STATUS_CODES.EMAIL_EXISTS);
        } else if(doesUserIdExist(userId)){
            logger.warn(String.format("User Id %s already exists in system", userId));
            result.setStatusCode(STATUS_CODES.USERID_EXISTS);
        } else {
            String cryptedPass = CryptUtil.getMessageDigest(user.getPassword());
            user.setPassword(cryptedPass);
            try {
                user = userRepository.save(user);
                if(user != null){
                    logger.info("User Created successfully " + user);
                    result.setStatusCode(STATUS_CODES.CREATE_OK);
                    user.setPassword(null);
                    result.setUser(user);
                } else {
                    logger.info("User Creation failed " + user);
                    result.setStatusCode(STATUS_CODES.OPERATION_FAILED);
                }
            } catch (Exception e) {
                logger.error("DAO Exception while creating user with id :" + user.getUserId(), e);
                throw new YartServiceException(e);
            }
            
        }
        return result;
    }

    

    @Override
    public UserWrapper modifyUser(User user) throws YartServiceException {
        if(user.getPassword()==null){
            User userFromDb = userRepository.findOne(user.getUserId());
            user.setPassword(userFromDb.getPassword());
        }
        validateFields(user);
        UserWrapper  result = new UserWrapper();
        user = userRepository.save(user);
        if(result.getUser()!=null){
            user.setPassword(null);
            result.setUser(user);
            result.setStatusCode(STATUS_CODES.MODIFY_OK);
        } else {
            result.setStatusCode(STATUS_CODES.OPERATION_FAILED);
        }
        return result;
    }

    @Override
    public boolean removeUser(String userId) throws YartServiceException {
        userRepository.delete(userId);
        return true;
    }

    @Override
    public boolean deactivateUser(String userId) throws YartServiceException {
        return changeUserActiveStatus(userId, false);
    }

    @Override
    public boolean activateUser(String userId) throws YartServiceException {
        return changeUserActiveStatus(userId, true);
    }
    
    private boolean changeUserActiveStatus(String userId, boolean newStatus) throws YartServiceException {
        User user = getUserById(userId);
        user.setActive(false);
        UserWrapper result = modifyUser(user);
        return result.getStatusCode().equals(STATUS_CODES.MODIFY_OK);
    }

    @Override
    public User getUserById(String userId) throws YartServiceException {
        return getUserById(userId, false);
    }
    
    private User getUserById(String userId, boolean needPassword) throws YartServiceException {
        try {
            User user = userRepository.findOne(userId);
            if(!needPassword){
                user.setPassword(null);
            }
            return user;
        } catch (Exception e) {
            logger.error("DAO Exception while getting user for id :" + userId, e);
            throw new YartServiceException(e);
        }
    }

    @Override
    public boolean doesUserIdExist(String userId) throws YartServiceException {
        return userRepository.exists(userId);
    }

    @Override
    public boolean doesEmailExist(String email) throws YartServiceException {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserWrapper modifyPassword(User user, String newPassword) throws YartServiceException {
        if(newPassword == null){
            return new UserWrapper(user, STATUS_CODES.OPERATION_FAILED);
        }
        UserWrapper result = verifyCredentials(user);
        if(STATUS_CODES.VALID_CREDENTIALS.equals(result.getStatusCode())){
            user = getUserById(user.getUserId());
            user.setPassword(CryptUtil.getMessageDigest(newPassword));
            modifyUser(user);
            result.setStatusCode(STATUS_CODES.MODIFY_OK);
        }
        return result;
    }

    @Override
    public boolean modifyForgottenPassword(User user, String newPassword) throws YartServiceException {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public UserWrapper verifyCredentials(User user) throws YartServiceException {
        String password = user.getPassword();
        String userId = user.getUserId();
        if(userId==null || password == null){
            throw new YartServiceException("UserId or Password is null");
        }
        password = CryptUtil.getMessageDigest(password);
        User userFromDB = getUserById(userId, true);
        UserWrapper result = new UserWrapper();
        if(userFromDB != null && password.equals(userFromDB.getPassword())){
            userFromDB.setPassword(null);
            result.setUser(userFromDB);
            result.setStatusCode(userFromDB.isActive() ? 
                    STATUS_CODES.VALID_CREDENTIALS : STATUS_CODES.USER_INACTIVE);
        } else {
            result.setStatusCode(STATUS_CODES.INVALID_CREDENTIALS);
        }
        return result;
    }

    private void validateFields(User user) throws InvalidValueForFieldException {
        if(!FormatUtils.isValidEmail(user.getEmail())){
            throw new InvalidValueForFieldException("email");
        }
        if(user.getPassword()==null || user.getPassword().isEmpty()){
            throw new InvalidValueForFieldException("password");
        }
        
    }
}
