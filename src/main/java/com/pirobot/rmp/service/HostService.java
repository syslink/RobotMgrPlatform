/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.service;

import java.util.List;
import com.pirobot.rmp.model.Host;

public  interface HostService
{

  public  void save(Host host);
  public  List<Host>  queryList();
  public void delete(String ip) ;
}