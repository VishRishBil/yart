package com.yart.user.service;

import com.yart.common.bean.UserWrapper;
import com.yart.common.exception.YartServiceException;
import com.yart.user.bean.User;

/**
 * User Service Interface
 * 
 * @author bilinbs
 *
 */
public interface UserService {

    /**
     * Create a new user profile
     * @param user
     * @return
     * @throws YartServiceException
     */
    public UserWrapper createUser(User user) throws YartServiceException;
    
    /**
     * Modifies existing user profile
     * @param user
     * @return
     * @throws YartServiceException
     */
    public UserWrapper modifyUser(User user) throws YartServiceException;
    
    /**
     * Remove a user profile
     * @param userId
     * @return
     * @throws YartServiceException
     */
    public boolean removeUser(String userId) throws YartServiceException;
    
    /**
     * Deactivates a user
     * @param userId
     * @return
     * @throws YartServiceException
     */
    public boolean deactivateUser(String userId) throws YartServiceException;
    
    /**
     * Sets user status to active
     * @param userId
     * @return
     * @throws YartServiceException
     */
    public boolean activateUser(String userId) throws YartServiceException;
    
    /**
     * Gets a user profile by id
     * @param userId
     * @return
     * @throws YartServiceException
     */
    public User getUserById(String userId) throws YartServiceException;
    
    /**
     * Checks whether a userId is already taken
     * @param userId
     * @return
     * @throws YartServiceException
     */
    public boolean doesUserIdExist(String userId) throws YartServiceException;
    
    /**
     * Checks whether a email id is already in use for another profile
     * @param email
     * @return
     * @throws YartServiceException
     */
    public boolean doesEmailExist(String email) throws YartServiceException;
    
    /**
     * Modify Password for a user
     * This requires the old password as well
     * @param user
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws YartServiceException
     */
    public UserWrapper modifyPassword(User user, String newPassword) throws YartServiceException;
    

    /**
     * Modify forgotten password
     * This method should be called after sufficient security checks
     * @param user
     * @param newPassword
     * @return
     * @throws YartServiceException
     */
    public boolean modifyForgottenPassword(User user, String newPassword) throws YartServiceException;
    
    /**
     * Verify whether the credentials are valid for the user
     * @param user
     * @return UserWrapper has User object (only if valid credentials) and status
     * @throws YartServiceException
     */
    public UserWrapper verifyCredentials(User user) throws YartServiceException; 
    
}
