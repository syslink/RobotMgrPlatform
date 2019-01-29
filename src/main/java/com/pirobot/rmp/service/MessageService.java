/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.service;

import java.util.List;

import com.pirobot.rmp.model.Message;
import com.pirobot.rmp.model.Page;

 
public interface MessageService {
	
	/**
	 * 保存通知信息
	 * @param MessageMO
	 */
	public void save(Message MessageMO);
	
	
	/**
	 * 修改通知信息
	 * @param MessageMO
	 */
	public void update(Message MessageMO);
	
	/**
	 * 修改通知信息状态
	 * @param MessageMO
	 */
	public void updateStatus(String gid,String status);
	
	/**
	 * 删除通知
	 * @param id
	 */
	public void delete(String id);
	
 
	/**
	 * 查看通知
	 * @param id
	 * @return MessageMO
	 */
	public Message queryById(String gid);
	
	 
	
	/**
	 * 根据条件查询通知列表
	 * @param mo 查询条件
	 * @return List<MessageMO>
	 */
	public void queryMessage(Message mo,Page page);
	/**
	 * 查询用户未正常接收的通知
	 * @param mo 查询条件
	 * @return List<MessageMO>
	 */
	public List<Message> queryOffLineMessages(String receiver);
	
	public void updateBatchReceived(String account);


	/**
	 * 删除过期的消息，超过3个月，并且已经接收过的
	 */
	public void deleteObsoleted();
 
}
