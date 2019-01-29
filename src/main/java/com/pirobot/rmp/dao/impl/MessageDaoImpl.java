 /**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pirobot.rmp.model.Message;
import com.pirobot.rmp.model.Page;
import com.pirobot.rmp.util.Constants;
import com.pirobot.rmp.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class MessageDaoImpl extends HibernateBaseDao<Message>{

	 
	
	

	public List<Message> queryMessageList(Message Message) {
		 
		DetachedCriteria criteria = mapingParam( Message);
	    criteria.addOrder(Order.asc("timestamp"));
	    return (List<Message>) getHibernateTemplate().findByCriteria(criteria);
	}
	
	
	public int queryMessageAmount(Message model) {
		DetachedCriteria criteria = mapingParam(model);
		criteria.setProjection(Projections.rowCount());
		List<?> list = getHibernateTemplate().findByCriteria(criteria);
		return Integer.valueOf(list.get(0).toString());
	}
	
	public void queryByPage(Message message, Page page) {
		DetachedCriteria criteria = mapingParam(message);
		criteria.addOrder(Order.desc("timestamp"));
		page.setDataList(getHibernateTemplate().findByCriteria(criteria,(page.getCurrentPage() - 1) * page.size,page.size));
	}
	
	
 

	
	private DetachedCriteria mapingParam(Message model)
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(Message.class);
	   
	    if (!StringUtil.isEmpty(model.getMid()))
	    {
	      criteria.add(Restrictions.eq("mid",model.getMid()));
	    }
	    if (!StringUtil.isEmpty(model.getSender()))
	    {
	      criteria.add(Restrictions.eq("sender",model.getSender()));
	    }
	    if (!StringUtil.isEmpty(model.getReceiver()))
	    {
	      criteria.add(Restrictions.eq("receiver",model.getReceiver()));
	    }
	    
	    if (!StringUtil.isEmpty(model.getAction()))
	    {
	         criteria.add(Restrictions.in("action", model.getAction().split(",")));
	    }
	    if (!StringUtil.isEmpty(model.getState()))
	    {
	      criteria.add(Restrictions.eq("state",model.getState()));
	    }
	  
	    return criteria;
	 }


	public void updateStatus(String mid, String state) {
		
		Session session = currentSession();
		Query query = session.createQuery("update Message set  state=?   where mid=?");
		query.setString(0, state);
		query.setString(1, mid);
		query.executeUpdate();
		
		
	}

	
	public void updateBatchReceived(String account) {
		
		Session session = currentSession();
		Query query = session.createQuery("update Message set  state=?   where state=?  and receiver=?");
		query.setString(0, Message.STATUS_RECEIVED);
		query.setString(1, Message.STATUS_NOT_RECEIVED);
		query.setString(2,account);
		query.executeUpdate();
		
	}


	public void deleteObsoleted() {
		Session session = currentSession();
		String sql = "delete from Message where state= :state   and timestamp >= :timestamp";
		Query query = session.createQuery(sql);
		query.setString("state",Message.STATUS_RECEIVED);
		query.setLong("timestamp",Constants.MESSAGE_LIFE_TIME);
		query.executeUpdate();  
	}

}
