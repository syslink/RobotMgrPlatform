/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */ 
package com.pirobot.web.tag;

import org.apache.commons.lang3.StringUtils;

public class Functions {

	public static String chatAt(String source, int index) {
		if (source == null || index > source.length() - 1 || index < 0) {
			return "";
		}
		return String.valueOf(source.charAt(index));
	}
	
	public static Long timeAgo(Long t) {
		if(t==0){
			return t;
		}
		return (System.currentTimeMillis() - t) / 1000;
	}
	
	public static String html(String text) {
		text=StringUtils.replace(text, "<", "&lt;");
		text=StringUtils.replace(text, ">", "&gt;");
		return text;
	}
}
