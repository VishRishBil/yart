package com.yart.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormatUtilsTest {

    @Test
    public void testIsValidEmail() {
        String email1 = "abc@xyz.com";
        String email2 = "abc_1@gmail.co.in";
        String email3 = "abd@gmail@yahoo.co.in";
        System.out.println(FormatUtils.isValidEmail(email1));
        System.out.println(FormatUtils.isValidEmail(email2));
        System.out.println(FormatUtils.isValidEmail(email3));
        assertTrue(FormatUtils.isValidEmail(email1));
        assertTrue(FormatUtils.isValidEmail(email2));
        assertFalse(FormatUtils.isValidEmail(email3));
    }

}
