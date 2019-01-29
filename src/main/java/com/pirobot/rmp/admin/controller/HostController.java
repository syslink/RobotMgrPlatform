/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.admin.controller;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pirobot.rmp.model.Host;
import com.pirobot.rmp.service.HostService;


@Controller
@RequestMapping("/console/host")
public class HostController {


	static Logger log = Logger.getLogger(HostController.class);
	@Autowired
	private HostService hostServiceImpl;

	public void setHostServiceImpl(HostService hostServiceImpl) {
		this.hostServiceImpl = hostServiceImpl;
	}

	@RequestMapping(value = "/list.action")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		model.addObject("list", hostServiceImpl.queryList());
		model.setViewName("host/manage");
		return model;
	}

	@RequestMapping(value = "/add.action")
	public @ResponseBody HashMap<String, Object> add(Host host) {
		
		HashMap<String, Object> datamap = new HashMap<String, Object>();
		try {
			datamap.put("code", 200);
			hostServiceImpl.save(host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datamap;
	}
	
	@RequestMapping(value = "/delete.action")
	public @ResponseBody HashMap<String, Object> delete(@RequestParam(value="ip") String ip ) {
		HashMap<String, Object> datamap = new HashMap<String, Object>();

		datamap.put("code", 200);
		hostServiceImpl.delete(ip);
		return datamap;
	}

}
