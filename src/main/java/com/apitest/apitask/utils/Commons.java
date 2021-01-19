package com.apitest.apitask.utils;

import java.text.MessageFormat;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Class for common utils methods
 * @author Jitender Sharma
 *
 */
public class Commons {
	
    /**
     * Format String with arguments
     * @param s
     * @param args
     * @return String
     */
    public static String msgFormat(String s, Object... args) {
        return new MessageFormat(s).format(args);
    }
    
    /**
     * Check the validity of Emails Format
     * @param email
     * @return Boolean
     */
    public static boolean isValidEmailAddress(String email) {
    	   boolean result = true;
    	   try {
    	      InternetAddress emailAddr = new InternetAddress(email);
    	      emailAddr.validate();
    	   } catch (AddressException ex) {
    	      result = false;
    	   }
    	   return result;
    	}

}
