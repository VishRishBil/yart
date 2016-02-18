package com.yart.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yart.common.exception.YartServiceException;

public class CryptUtilTest {

    @Test
    public void testGetMessageDigest() {
        String message1 = "password";
        String message2 = new String("password");
        String digest1, digest2;
        
        try {
            digest1 = CryptUtil.getMessageDigest(message1);
            digest2 = CryptUtil.getMessageDigest(message2);
            System.out.println(digest1);
            System.out.println(digest2);
            assertEquals(digest1, digest2);
            
        } catch (YartServiceException e) {
            e.printStackTrace();
            fail("Exception while getting digest");
        }
        
        
    }

}
