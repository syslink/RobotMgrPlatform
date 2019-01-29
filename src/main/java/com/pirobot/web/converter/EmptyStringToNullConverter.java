/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.web.converter;
import org.springframework.core.convert.converter.Converter;


 
public class EmptyStringToNullConverter implements  Converter<String,String>  {

	@Override
	public String convert(String arg) {
		if(arg!=null && arg.length()==0){
			return null;
		}
		return arg;
	}

  
}
