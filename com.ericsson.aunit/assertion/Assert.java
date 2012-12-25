/* 
 * Created : Dec 26, 2012 
 * 
 * Copyright (c) 2012 Ericsson AB, Sweden. 
 * All rights reserved. 
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden. 
 * The program(s) may be used and/or copied with the written permission from Ericsson AB 
 * or in accordance with the terms and conditions stipulated in the agreement/contract 
 * under which the program(s) have been supplied. 
 */ 
package assertion;

import exception.AssertFailException;

/**
 * @author eyanjsh
 *
 */
public class Assert
{
    public static final void assertTrue(boolean condition){
        if(!condition){
            fail(null);
        }
    }

    public static final void assertTrue(boolean condition, String messageOnError){
        if(!condition){
            fail(messageOnError);
        }
    }

    public static final void assertEquals(int actual, int expected, String messageWhenNotEqual){
        if(actual != expected){
            compareFail(new Integer(actual), new Integer(expected), messageWhenNotEqual);
        }
    }

    public static final void assertEquals(Object actual, Object expected, String messageWhenNotEqual){
        if(expected == null && actual != null){
            compareFail(actual, expected, messageWhenNotEqual);
        } else{
            if(actual == null || !actual.equals(expected)){
                compareFail(actual, expected, messageWhenNotEqual);
            }
        }
    }

    private static void compareFail(Object actual, Object expected, String messageWhenNotEqual)
    {
        fail("Expected: [" + expected.toString() + "], but: [" + actual + "], " + messageWhenNotEqual);
    }
    
    private static void fail(String msg) {
        throw new AssertFailException(msg);
    }
}
