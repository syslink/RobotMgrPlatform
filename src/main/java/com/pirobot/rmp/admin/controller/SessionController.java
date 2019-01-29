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
import org.springframework.web.servlet.ModelAndView;

import com.pirobot.rmp.model.CIMSession;
import com.pirobot.rmp.model.Page;
import com.pirobot.rmp.service.RobotSessionService;
@Controller    
@RequestMapping("/console/session") 
public class SessionController  {

	@Autowired
	private RobotSessionService robotSessionService;

	
	
	public void setRobotSessionService(RobotSessionService robotSessionService) {
		this.robotSessionService = robotSessionService;
	}

	@RequestMapping(value="/list.action")  
	public ModelAndView list(@ModelAttribute("cimsession") CIMSession cimsession,@ModelAttribute("page") Page page){  
		
		ModelAndView model = new ModelAndView();
		robotSessionService.queryPage(cimsession, page);
 		model.setViewName("session/manage");
 		
 		return model;
	}
	 
}
