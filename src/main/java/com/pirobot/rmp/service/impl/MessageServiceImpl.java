/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.service.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pirobot.rmp.model.Message;
import com.pirobot.rmp.model.Page;
import com.pirobot.rmp.dao.impl.MessageDaoImpl;
import com.pirobot.rmp.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService {
	protected final Logger logger = Logger .getLogger(MessageServiceImpl.class);

	@Autowired
	private MessageDaoImpl messageDao;


	@Override
	public void delete(String gid) {
		Message msg = new Message();
		msg.setMid(gid);
		messageDao.delete(msg);
	}

	@Override
	public Message queryById(String gid) {
		// TODO Auto-generated method stub
		return messageDao.get(gid);
	}

	@Override
	public void queryMessage(Message mo,Page page) {
		int count = this.messageDao.queryMessageAmount(mo);
		page.setCount(Integer.valueOf(count));
		if (page.getCount() == 0) {
			return  ;
		}
		
		this.messageDao.queryByPage(mo, page);
	}

	@Override
	public List<Message> queryOffLineMessages(String receiver) {
		 Message msg = new Message();
		 msg.setReceiver(receiver);
		 msg.setState(Message.STATUS_NOT_RECEIVED);
		 return messageDao.queryMessageList(msg);
	}

	@Override
	public void save(Message obj) {

		obj.setState(Message.STATUS_NOT_RECEIVED);
		messageDao.save(obj);

	}

	@Override
	public void update(Message obj) {
		// TODO Auto-generated method stub
		messageDao.update(obj);
	}

	@Override
	public void updateStatus(String gid, String status) {
		messageDao.updateStatus(gid,status);
	}

	public void setMessageDao(MessageDaoImpl messageDao) {
		this.messageDao = messageDao;
	}

	 
 

	@Override
	public void updateBatchReceived(String account) {
		// TODO Auto-generated method stub
		messageDao.updateBatchReceived(account);
	}

	@Override
	public void deleteObsoleted() {
		// TODO Auto-generated method stub
		messageDao.deleteObsoleted();
	}

 
 
 

}
