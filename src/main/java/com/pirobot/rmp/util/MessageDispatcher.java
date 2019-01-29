/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */ 
package com.pirobot.rmp.util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.pirobot.rmp.model.Message;

 
public class MessageDispatcher  {
    private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	private static ThreadPoolExecutor executor =  new ThreadPoolExecutor(3, 5, 20,	TimeUnit.SECONDS,queue);;
	final static String API_SEND_MESSAGE_URL = ConfigManager.getInstance().getStringValue("MESSAGE_SEND_URL");

    public static void  execute(final Message msg,final String ip)
	{
	    executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					httpPost(String.format(API_SEND_MESSAGE_URL,ip),msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 });
	}
 
	
	private static String httpPost(String url,Message msg) throws Exception 
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("mid", msg.getMid()));
        nvps.add(new BasicNameValuePair("action", msg.getAction()));
        nvps.add(new BasicNameValuePair("content",msg.getContent()));
        nvps.add(new BasicNameValuePair("sender", msg.getSender()));
        nvps.add(new BasicNameValuePair("receiver",msg.getReceiver()));
        nvps.add(new BasicNameValuePair("format",msg.getFormat()));
        nvps.add(new BasicNameValuePair("timestamp",String.valueOf(msg.getTimestamp())));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String data = EntityUtils.toString(response.getEntity());
        response.close();
		 
        return data;
	}
	
  
}
