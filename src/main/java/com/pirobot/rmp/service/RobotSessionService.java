/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.service;


import java.util.List;

import com.pirobot.rmp.model.CIMSession;
import com.pirobot.rmp.model.Page;
 
public  interface RobotSessionService
{
	  public void add(CIMSession session);
	  public  CIMSession get(String deviceId);
	  public  void queryPage(CIMSession session ,Page page);
	  public  List<CIMSession> queryOnlineList(CIMSession session);
	  public void remove(String deviceId);
	  public void updateState(String deviceId,int state);
	  public void update(CIMSession session);
}