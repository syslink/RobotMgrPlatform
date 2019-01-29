/**
 * probject:cim
 *  
 * @version 2.0.0
 * @author oursyslink@163.com
 */
package com.pirobot.rmp.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_robot_host")

 
/**
 *服务器IP配置，多台服务器集群时需要配置每个服务器的IP
 */
public class Host implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ip", length = 15)
	private String ip;
  
	@Column(name = "descrption", length = 100)
	private String descrption;
	
	
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getDescrption() {
		return descrption;
	}


	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}


	public String toString()
	{
		StringBuffer buffer = new   StringBuffer();
		buffer.append("{");
		buffer.append("ip:").append("'").append(ip==null?"":ip).append("'");
		buffer.append(",").append("descrption:").append("'").append(descrption==null?"":descrption).append("'");
		buffer.append("}");
		return buffer.toString();
	}
	
	
	
}
