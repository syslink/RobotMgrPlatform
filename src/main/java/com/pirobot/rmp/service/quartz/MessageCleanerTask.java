package com.pirobot.rmp.service.quartz;


import org.springframework.beans.factory.annotation.Autowired;

import com.pirobot.rmp.service.MessageService;

public class MessageCleanerTask {
	@Autowired
	private MessageService messageServiceImpl;
	
	 
	public void doTask() {
		messageServiceImpl.deleteObsoleted();
	}

	public void setMessageServiceImpl(MessageService messageServiceImpl) {
		this.messageServiceImpl = messageServiceImpl;
	}
 
}
