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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import annotation.After;
import annotation.Before;
import annotation.Test;

/**
 * @author eyanjsh
 *
 */
public class Utils
{
    public static final List<Method> findBeforeMethods(Class<?> clazz)
    {
        List<Method> beforeMethods = findMethodsWithAnnotation(clazz, Before.class);
        return beforeMethods;
    }
    
    public static final List<Method> findAfterMethods(Class<?> clazz)
    {
        List<Method> afterMethods = findMethodsWithAnnotation(clazz, After.class);
        return afterMethods;
    }
    
    public static final List<Method> findTestMethods(Class<?> clazz)
    {
        List<Method> testMethods = findMethodsWithAnnotation(clazz, Test.class);
        return testMethods;
    }

    public static final List<Method> findMethodsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClazz)
    {
        List<Method> list = new ArrayList<Method>();

        if (clazz != null && annotationClazz != null)
        {
            Method[] methods = clazz.getMethods();
            for (Method method : methods)
            {
                if (method.isAnnotationPresent(annotationClazz))
                {
                    list.add(method);
                }
            }
        }

        return list;
    }
}
