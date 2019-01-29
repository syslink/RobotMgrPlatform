/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.api.controller;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pirobot.rmp.api.result.BaseResult;
import com.pirobot.rmp.cim.client.CIMConnector;
import com.pirobot.rmp.model.Message;
import com.pirobot.rmp.service.MessageService;
@Controller    
@RequestMapping("/cgi/message")
public class APIMessageController {

	/**
	 * 
	 */
	@Autowired
	private MessageService messageServiceImpl;
	
	private Map<String, String> listencedSentenceMap = new ConcurrentHashMap<String, String>();

	@RequestMapping(value="/send.api")  
	public @ResponseBody BaseResult send(Message message)  {

		BaseResult result = new BaseResult();
		int code = CIMConnector.pushMessage(message);
		result.code = code;
		result.data = message.getMid();
		return result;
	}

	@RequestMapping(value="/received.api")  
	public @ResponseBody BaseResult received(Message message)  {

		BaseResult result = new BaseResult();

		
		messageServiceImpl.updateStatus(message.getMid(),Message.STATUS_RECEIVED);
		
		return result;
	}

	@RequestMapping(value="/delete.api")  
	public @ResponseBody BaseResult delete(String mid)  {

		BaseResult result = new BaseResult();
		
		messageServiceImpl.delete(mid);

		return result;
	}


	@RequestMapping(value="/offlineList.api")  
	public @ResponseBody BaseResult offlineList(String receiver)  {

		BaseResult result = new BaseResult();
		if (StringUtils.isEmpty(receiver))
		{
			result.code = 403;
		}
		else
		{
			result.dataList = messageServiceImpl.queryOffLineMessages(receiver);
		}

		return result;
	}
	
	@RequestMapping(value="/batchReceive.api")  
	public @ResponseBody BaseResult batchReceive(String receiver)  {

		BaseResult result = new BaseResult();

        messageServiceImpl.updateBatchReceived(receiver);
		
        return result;
	}
	
	
	@RequestMapping(value="/broadcastOnline.api")  
	public @ResponseBody BaseResult broadcastOnline(Message message)  {
		BaseResult result = new BaseResult();
		
		return result;
	}

	@RequestMapping(value="/putListenedSentence.api")  
	public @ResponseBody BaseResult putListenedSentence(String sentenceId, String sentence)  {
		BaseResult result = new BaseResult();		
		listencedSentenceMap.put(sentenceId, sentence);
		return result;
	}

	@RequestMapping(value="/getListenedSentence.api")  
	public @ResponseBody BaseResult getListenedSentence(String sentenceId)  {
		BaseResult result = new BaseResult();		
		if(listencedSentenceMap.containsKey(sentenceId))
		{
			result.code = 200;
			result.data = listencedSentenceMap.get(sentenceId);
		}
		else
		{
			result.code = 404;
		}
		return result;
	}
	
	public void setMessageServiceImpl(MessageService messageServiceImpl) {
		this.messageServiceImpl = messageServiceImpl;
	}

}
