/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */ 
package com.pirobot.rmp.util;

import org.springframework.context.ApplicationContext;
 
public class ContextHolder  {

    private static  ApplicationContext context;
    
    public static void setContext(ApplicationContext ac)
    {
    	context =ac;
    }
    
    
    public static  <T> T getBean(Class<T> c)
    {
    	return context.getBean(c);
    }
}