/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pirobot.rmp.api.result.BaseResult;
import com.pirobot.rmp.cim.client.CIMConnector;
import com.pirobot.rmp.model.Message;
import com.pirobot.rmp.util.StringUtil;
@Controller    
@RequestMapping("/cgi/script")
public class APIScriptController {
 

	@RequestMapping(value="/execute.api")  
	public @ResponseBody BaseResult execute(String content,String receiver)  {

		BaseResult result = new BaseResult();
		Message message = new Message();
		message.setMid(StringUtil.getUUID());
		message.setReceiver(receiver);
		message.setContent(content);
		int code = CIMConnector.executeScript(message);
		result.code = code;

		return result;
	}

}
