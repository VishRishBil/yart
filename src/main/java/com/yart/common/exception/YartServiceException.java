package com.yart.common.exception;

/**
 * Service Exception
 * @author bilinbs
 *
 */
public class YartServiceException extends Exception {

    private static final String DEFAULT_MESSAGE = "Service Exception occurred";
    
    private static final long serialVersionUID = 1L;
    
    public YartServiceException(String message){
        super(message);
    }
    
    public YartServiceException(Throwable cause){
        super(DEFAULT_MESSAGE, cause);
    }
    
    public YartServiceException(String message, Throwable cause){
        super(message, cause);
    }

}
