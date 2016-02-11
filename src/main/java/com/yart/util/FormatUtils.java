package com.yart.util;

/**
 * Util class for formatting and formats 
 * @author bilinbs
 *
 */
public class FormatUtils {
    
    private FormatUtils(){
    }
    
    public static final String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    
    public static boolean isValidEmail(String email){
        if(email == null || email.isEmpty()){
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
    
}
