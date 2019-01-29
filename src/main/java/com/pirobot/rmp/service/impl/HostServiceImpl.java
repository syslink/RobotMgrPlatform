/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pirobot.rmp.dao.impl.HostDaoImpl;
import com.pirobot.rmp.model.Host;
import com.pirobot.rmp.service.HostService;
/**
 * 服务器集群时，每台服务器的IP配置管理
 */
@Service
public class HostServiceImpl implements HostService {
	
	@Autowired
	private HostDaoImpl hostDao;

	public void setHostDao(HostDaoImpl hostDao) {
		this.hostDao = hostDao;
	}

	@Override
	public void delete(String ip) {
		Host host = new Host();
		host.setIp(ip);
		hostDao.delete(host);
	}

	@Override
	public List<Host> queryList() {
		return hostDao.getAll();
	}

	@Override
	public void save(Host host) {
		hostDao.save(host);
	}

}