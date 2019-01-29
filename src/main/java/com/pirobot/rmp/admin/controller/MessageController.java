/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */  
package com.pirobot.rmp.admin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pirobot.rmp.api.result.BaseResult;
import com.pirobot.rmp.model.Message;
import com.pirobot.rmp.model.Page;
import com.pirobot.rmp.service.MessageService;

@Controller
@RequestMapping("/console/message")
public class MessageController   {

	@Autowired
	private MessageService messageServiceImpl;

	@RequestMapping(value="/list.action")  
	public ModelAndView list(@ModelAttribute("message")Message message,@ModelAttribute("page") Page page) {  

		  ModelAndView model = new ModelAndView();
		  messageServiceImpl.queryMessage(message,page);
 		  model.setViewName("message/manage");
 		  return model;
 		  
	}
	
	@RequestMapping(value="/delete.action")  
	public @ResponseBody BaseResult  delete(@RequestParam(value="mid") String mid) {
		BaseResult result = new BaseResult();
		messageServiceImpl.delete(mid);
		return result;
	}
	 
	
	public void setMessageServiceImpl(MessageService messageServiceImpl) {
		this.messageServiceImpl = messageServiceImpl;
	}

 

 
}
