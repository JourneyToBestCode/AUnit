/* 
 * Created : Dec 25, 2012 
 * 
 * Copyright (c) 2012 Ericsson AB, Sweden. 
 * All rights reserved. 
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden. 
 * The program(s) may be used and/or copied with the written permission from Ericsson AB 
 * or in accordance with the terms and conditions stipulated in the agreement/contract 
 * under which the program(s) have been supplied. 
 */ 
package facilities;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author eyanjsh
 *
 */
public class ExecuteContext
{
    private Class<?> clazz;
    private Object   instance;
    private List<Method> testMethods;
    private List<Method> beforeMethod;
    private List<Method> afterMethod;
    public Class<?> getClazz()
    {
        return clazz;
    }
    public void setClazz(Class<?> clazz)
    {
        this.clazz = clazz;
    }
    public Object getInstance()
    {
        return instance;
    }
    public void setInstance(Object instance)
    {
        this.instance = instance;
    }
    public List<Method> getTestMethods()
    {
        return testMethods;
    }
    public void setTestMethods(List<Method> testMethods)
    {
        this.testMethods = testMethods;
    }
    public List<Method> getBeforeMethod()
    {
        return beforeMethod;
    }
    public void setBeforeMethod(List<Method> beforeMethod)
    {
        this.beforeMethod = beforeMethod;
    }
    public List<Method> getAfterMethod()
    {
        return afterMethod;
    }
    public void setAfterMethod(List<Method> afterMethod)
    {
        this.afterMethod = afterMethod;
    }
}
