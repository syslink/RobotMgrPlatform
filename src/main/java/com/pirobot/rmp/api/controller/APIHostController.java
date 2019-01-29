/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */ 
package com.pirobot.rmp.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pirobot.rmp.model.Host;
import com.pirobot.rmp.service.HostService;
/**
 * 服务器调度实现
 */

@Controller    
@RequestMapping("/cgi/host")
public class APIHostController {

 
	
	static Logger log = Logger.getLogger(APIHostController.class);
	@Autowired
	private HostService hostServiceImpl;
 
	 
	public void setHostServiceImpl(HostService hostServiceImpl) {
		this.hostServiceImpl = hostServiceImpl;
	}

    /**
     * 客户登录时调用此接口，为客户端随机分配一台服务器
     * @throws IOException
     */
	@RequestMapping(value="/dispense.api")  
	public @ResponseBody HashMap<String, Object> dispense()  
	{
		
		List<Host> list = hostServiceImpl.queryList();
		HashMap<String,Object> datamap = new  HashMap<String,Object>();
		datamap.put("code", 200);
		datamap.put("data", list.get(new Random().nextInt(list.size())).getIp());
		return datamap;
	}
	
	 
	
 
}
