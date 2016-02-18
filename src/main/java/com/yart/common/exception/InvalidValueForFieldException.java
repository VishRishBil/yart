package com.yart.common.exception;

public class InvalidValueForFieldException extends YartServiceException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String messageStringWithValue = 
            "Value : %s is invalid for the field '%s'";
    
    private static final String messageString = "Invalid value for the field '%s'";
    
    public InvalidValueForFieldException(String fieldId, String value) {
        super(String.format(messageStringWithValue, fieldId, value));
    }
    
    public InvalidValueForFieldException(String fieldId){
        super(String.format(messageString, fieldId));
    }

}
