package com.yart.base;

import java.util.List;

import com.yart.common.exception.YartDaoException;

/**
 * Base Interface for all DAOs
 * @author bilinbs
 *
 * @param <ENTITY>
 */

public interface BaseDAO<ENTITY> {

    public boolean save(ENTITY e)throws YartDaoException;
    
    public boolean update(ENTITY e)throws YartDaoException;
    
    public boolean remove(ENTITY e)throws YartDaoException;
    
    public ENTITY getById(Object id)throws YartDaoException;
    
    public List<ENTITY> getListByIds(Object[] ids)throws YartDaoException;
}
