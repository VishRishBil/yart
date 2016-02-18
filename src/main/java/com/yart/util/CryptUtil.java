package com.yart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.yart.common.exception.YartServiceException;



/**
 * Util Class for encryption and related stuff
 * @author bilinbs
 *
 */
public class CryptUtil {
    
    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(CryptUtil.class);
    
    /**
     * Private constructor to prevent instantiation
     */
    private CryptUtil(){
        
    }
    
    /**
     * Encrypts the message using SHA-256
     * @param message
     * @return
     * @throws YartServiceException 
     */
    public static String getMessageDigest(String message) throws YartServiceException{
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha256.digest(message.getBytes());
            return new String(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Exception while getting message digest", e);
            throw new YartServiceException("Exception while getting message digest", e);
        }
        
    }

}
