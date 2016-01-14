/**
 * 
 */
package com.yart.common.exception;

/**
 * DAO Exception
 * @author bilinbs
 *
 */
public class YartDaoException extends Exception {

    private static final String DEFAULT_MESSAGE = "DAO Exception occurred";
    
    private static final long serialVersionUID = 1L;
    
    public YartDaoException(String message){
        super(message);
    }
    
    public YartDaoException(Throwable cause){
        super(DEFAULT_MESSAGE, cause);
    }
    
    public YartDaoException(String message, Throwable cause){
        super(message, cause);
    }

}
