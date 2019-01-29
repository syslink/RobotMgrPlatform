package com.pirobot.rmp.cim.client;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.log4j.Logger;

import com.pirobot.cim.sdk.client.CIMEventBroadcastReceiver;
import com.pirobot.cim.sdk.client.CIMEventListener;
import com.pirobot.cim.sdk.client.CIMPushManager;
import com.pirobot.cim.sdk.client.constant.CIMConstant;
import com.pirobot.cim.sdk.client.model.Message;
import com.pirobot.cim.sdk.client.model.ReplyBody;
import com.pirobot.cim.sdk.client.model.SentBody;
import com.pirobot.rmp.util.ConfigManager;
import com.pirobot.rmp.util.StringUtil;


public class CIMConnector {
	protected final static Logger logger = Logger.getLogger(CIMConnector.class);
    private static boolean isConnected = false;
    private final static int CIM_PORT = 23456;
    private static LinkedBlockingQueue<com.pirobot.rmp.model.Message> messageQueue = new LinkedBlockingQueue<com.pirobot.rmp.model.Message>();
	/**
	 * @param args
	 */
	public static void init() {
		 CIMEventBroadcastReceiver.getInstance().setGlobalCIMEventListener(new CIMEventListener(){
			@Override
			public void onConnectionClosed() {
				logger.info("onConnectionClosed");
				isConnected = false;
			}

			@Override
			public void onConnectionFailed(Exception e) {
				logger.info("onConnectionFailed");
				isConnected = false;
			}

			@Override
			public void onConnectionSuccessed() {
				logger.info("onConnectionSuccessed");
				isConnected = true;
				
				com.pirobot.rmp.model.Message message = messageQueue.poll();
				if(message!=null){
					pushMessage(message);
				}
			}

			@Override
			public void onMessageReceived(Message message) {
			}

			@Override
			public void onReplyReceived(ReplyBody replybody) {
				logger.info("onReplyReceived:"+replybody.toXmlString());
			}}
		 );
		 String host = ConfigManager.getInstance().getStringValue("CIM_SERVER_HOST");
		 logger.info("connect to cim>"+host+":"+CIM_PORT);
		 CIMPushManager.connect( host, CIM_PORT);
	}
	
	
	public static int pushMessage(com.pirobot.rmp.model.Message msg){
		
		SentBody sent = new SentBody();
		sent.setKey(CIMConstant.RequestKey.CLIENT_PUSH_MESSAGE);

		if (msg.getMid() == null) {
			msg.setMid(StringUtil.getUUID());
		}
		
		sent.put("mid", msg.getMid());
        sent.put("action", msg.getAction());
        sent.put("content",msg.getContent());
        sent.put("sender", msg.getSender());
        sent.put("receiver",msg.getReceiver());
        sent.put("format",msg.getFormat());
        if(isConnected)
        {
        	 CIMPushManager.sendRequest(sent);
        	 return 200;
        }else{
        	messageQueue.offer(msg);
        	return 404;
        }
       
		
	}
	
	
public static int executeScript(com.pirobot.rmp.model.Message msg){
		
		SentBody sent = new SentBody();
		sent.setKey(CIMConstant.RequestKey.CLIENT_EXECUTE_SCRIPT);
		sent.put("receiver",msg.getReceiver());
        sent.put("content",msg.getContent());
        sent.put("timestamp",String.valueOf(msg.getTimestamp()));
        sent.put("mid", msg.getMid());
        if(isConnected)
        {
        	 CIMPushManager.sendRequest(sent);
        	 return 200;
        }else{
        	messageQueue.offer(msg);
        	return 404;
        }
		
	}
 

}
