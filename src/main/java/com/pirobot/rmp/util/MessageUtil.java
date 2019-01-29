/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.util;

import com.pirobot.rmp.model.Message;




public class MessageUtil {
	 
	 
		public static  Message  clone(Message msg)
		{
			Message m = new Message();
			m.setContent( msg.getContent());
			m.setFormat( msg.getFormat());
			m.setReceiver( msg.getReceiver());
			m.setSender( msg.getSender());
			m.setAction(msg.getAction());
			m.setMid(msg.getMid());
			m.setTimestamp(msg.getTimestamp());
			return m;
		}
		
}
