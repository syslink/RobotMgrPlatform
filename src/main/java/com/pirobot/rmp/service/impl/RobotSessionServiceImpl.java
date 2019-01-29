/**
 * probject:cim
 * @version 2.0.0
 * 
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pirobot.rmp.model.CIMSession;
import com.pirobot.rmp.model.Page;
import com.pirobot.rmp.dao.impl.CIMSessionDaoImpl;
import com.pirobot.rmp.service.RobotSessionService;
import com.pirobot.rmp.util.StringUtil;
 

/**
 * 集群 session管理实现示例， 各位可以自行实现 AbstractSessionManager接口来实现自己的 session管理
 *服务器集群时 须要将CIMSession 信息存入数据库或者nosql 等 第三方存储空间中，便于所有服务器都可以访问
 */
@Service
public class RobotSessionServiceImpl implements RobotSessionService{

	@Autowired
	CIMSessionDaoImpl CIMSessionDao;
    
    /**
     *  
     */
    public void add(CIMSession session) {
         
    	
    	session.setGid(StringUtil.getUUID());
    	CIMSessionDao.deleteByAccount(session.getDeviceId());
        /**
         * 下面 将session 存入数据库
         */
    	CIMSessionDao.save(session);
    	
        
    }

     
    public CIMSession get(String deviceId) {
    	
    	//这里查询数据库 
    	 
    	CIMSession session = CIMSessionDao.querySingleByDeviceId(deviceId);
    	
    	return session;
    }
    

     
    public List<CIMSession> queryAll() {
    	List<CIMSession> list = CIMSessionDao.getAll();
    	return list;
    }
 
     
    public void  remove(String account) {
        
    	
    	CIMSessionDao.deleteByAccount(account);
    }
    
    

	public void setCIMSessionDao(CIMSessionDaoImpl sessionDao) {
		CIMSessionDao = sessionDao;
	}
 
 
	@Override
	public void updateState(String account,int state) {
		CIMSessionDao.updateStatus(account,state);
	}


	@Override
	public void update(CIMSession session) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<CIMSession> queryOnlineList(CIMSession session) {
		// TODO Auto-generated method stub
		return CIMSessionDao.queryOnlineList( session);
	}


	@Override
	public void queryPage(CIMSession session, Page page) {
		int count = this.CIMSessionDao.querySessionAmount(session);
		page.setCount(Integer.valueOf(count));
		if (page.getCount() == 0) {
			return  ;
		}
		
		this.CIMSessionDao.queryByPage(session, page);
	}

 
 
}
