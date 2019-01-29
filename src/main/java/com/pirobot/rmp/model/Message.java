/**
 * probject:cim-server-sdk
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */ 
package com.pirobot.rmp.model;

import java.io.Serializable;
/**
 * 消息对象
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String STATUS_NOT_RECEIVED = "0";//未接受
	public static final String STATUS_RECEIVED = "1";//已接收
	public static final String STATUS_READ = "2";//已读取
	
	/**
	 * 消息类型，用户自定义消息类别
	 */
	private String mid;
	
	
	/**
	 * 消息类型，用户自定义消息类别
	 */
	private String action;
 
	/**
	 * 消息类容，于action 组合为任何类型消息，content 根据 format 可表示为 text,json ,xml数据格式
	 */
	private String content;

	/**
	 * 消息发送者账号
	 */
	private String sender;
	/**
	 * 消息发送者接收者
	 */
	private String receiver;
   
	/**
	 * content 内容格式
	 */
	private String format;

	
	private long timestamp;
	
	
	/**
	 * content 内容格式
	 */
	private String state;
	
	public Message()
	{
		timestamp = System.currentTimeMillis();
	}
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	 

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	 
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	 

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public String toString() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<message>");
		buffer.append("<mid>").append(mid).append("</mid>");

		if (isNotEmpty(action)) {
			buffer.append("<action>").append(action).append("</action>");
		}

		if (isNotEmpty(sender)) {
			buffer.append("<sender>").append(sender).append("</sender>");
		}

		if (isNotEmpty(receiver)) {
			buffer.append("<receiver>").append(receiver).append("</receiver>");
		}

		if (isNotEmpty(format)) {
			buffer.append("<format>").append(format).append("</format>");
		}

		if (timestamp > 0) {
			buffer.append("<timestamp>").append(timestamp).append("</timestamp>");
		}

		buffer.append("</message>");
		return buffer.toString();
	}

	public String toXmlString() {

		return toString();
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public boolean isNotEmpty(String txt) {
		return txt != null && !txt.isEmpty();
	}

	
}
