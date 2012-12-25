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
package exception;

/**
 * @author eyanjsh
 *
 */
public class AssertFailException extends AssertionError
{

    /**
     * 
     */
    private static final long serialVersionUID = 8522154312208540323L;

    public AssertFailException(String message)
    {
        super(message);
    }

}
