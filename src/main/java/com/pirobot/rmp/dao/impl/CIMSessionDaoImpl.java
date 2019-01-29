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

import com.pirobot.rmp.model.CIMSession;
import com.pirobot.rmp.model.Page;
import com.pirobot.rmp.util.StringUtil;
@Repository
@SuppressWarnings("unchecked")
public class CIMSessionDaoImpl extends HibernateBaseDao<CIMSession>
{

	 
 

	public CIMSession querySingleByDeviceId(String deviceId)
	{
		
		DetachedCriteria criteria=DetachedCriteria.forClass(CIMSession.class);
		criteria.add(Restrictions.eq("deviceId", deviceId));
		List<CIMSession> list = (List<CIMSession>) getHibernateTemplate().findByCriteria(criteria);
		return (list == null || list.isEmpty())?null:list.get(0);
	}
	 
	

	public List<CIMSession> queryOnlineList(CIMSession session)
	{
		
		DetachedCriteria criteria=DetachedCriteria.forClass(CIMSession.class);
		criteria.add(Restrictions.eq("status", CIMSession.STATUS_ENABLED));
		return (List<CIMSession>) getHibernateTemplate().findByCriteria(criteria);
	}
	 
	
	public void deleteByAccount(String deviceId) {
		Session session = currentSession();
		String sql = "delete   CIMSession   where deviceId = :deviceId";
		Query query = session.createQuery(sql);
		query.setParameter("deviceId", deviceId);
		query.executeUpdate();
	}
	

	public void updateStatus(String deviceId, int status) {
		Session session = currentSession();
		String sql = "update   CIMSession  set status = :status where deviceId = :deviceId";
		Query query = session.createQuery(sql);
		query.setParameter("deviceId", deviceId);
		query.setParameter("status", status);
		query.executeUpdate();
	}
	
	public int querySessionAmount(CIMSession cimsession) {
		DetachedCriteria criteria = mapingParam(cimsession);
		criteria.setProjection(Projections.rowCount());
		List<?> list = getHibernateTemplate().findByCriteria(criteria);
		return Integer.valueOf(list.get(0).toString());
	}
	
	public void queryByPage(CIMSession cimsession, Page page) {
		DetachedCriteria criteria = mapingParam(cimsession);
		criteria.addOrder(Order.desc("bindTime"));
		page.setDataList(getHibernateTemplate().findByCriteria(criteria,(page.getCurrentPage() - 1) * page.size,page.size));
	}
	
	
 

	
	private DetachedCriteria mapingParam(CIMSession model)
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(CIMSession.class);
	   
	    if (!StringUtil.isEmpty(model.getDeviceId()))
	    {
	      criteria.add(Restrictions.eq("deviceId",model.getDeviceId()));
	    }
	    if (!StringUtil.isEmpty(model.getHost()))
	    {
	      criteria.add(Restrictions.eq("host",model.getHost()));
	    }
	    
	    if (!StringUtil.isEmpty(model.getClientVersion()))
	    {
	    	 criteria.add(Restrictions.eq("clientVersion",model.getClientVersion()));
	    }
	    
	    return criteria;
	 }


}