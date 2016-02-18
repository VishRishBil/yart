package com.yart.user.dao;

import java.util.List;

import com.yart.base.BaseDAO;
import com.yart.common.exception.YartDaoException;
import com.yart.user.bean.User;

public interface UserDao extends BaseDAO<User>{
    
    public User getUserByUserId(String userId) throws YartDaoException;
    
    public User getUserByEmail(String email) throws YartDaoException;
    
    public List<User> getUsersByName(String name) throws YartDaoException;
    
    public String getPasswordForUser(String userId) throws YartDaoException;
    
    public boolean savePasswordForUser(String userId, String password) throws YartDaoException;
    

}
