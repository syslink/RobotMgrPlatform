/**
 * probject:cim-core
 * @version 1.5.0
 * 
 * @author oursyslink@163.com
 */ 
package com.pirobot.rmp.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * IoSession包装类,集群时 将此对象存入表中
 */

public class CIMSession  implements Serializable{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = 1L;
	public  transient static String ID = "ID";
	public  transient static String HOST = "HOST";
    public  transient static final int STATUS_ENABLED = 0;
    public  transient static final int STATUS_DISABLED = 1;
    
    public  transient static String CHANNEL_IOS = "ios";
    public  transient static String CHANNEL_ANDROID = "android";
    public  transient static String CHANNEL_WINDOWS = "windows";
    public  transient static String CHANNEL_WP = "wp";
    public  transient static String CHANNEL_LINUX = "linux";
    
	private String gid;//session全局ID
	private long nid;//session在本台服务器上的ID
	private String deviceId;//客户端ID  (设备号码+应用包名),ios为devicetoken
	private String host;//session绑定的服务器IP
	private String channel;//终端设备类型
	private String deviceModel;//终端设备型号
	private String deviceName;//终端设备名称
	private String clientVersion;//终端应用版本
	private String systemVersion;//终端系统版本
	private Long bindTime;//登录时间
	private Double longitude;//经度
	private Double latitude;//维度
	private String location;//位置
	private String geohash;//位置
	private int status;// 状态
	 
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}
	
	

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		
		this.gid = gid;
		
	}

	public Long getNid() {
		return nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	public String getDeviceId() {
		return deviceId;
	}


	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
		
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
		
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
		
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;		
	}

	public String getHost() {
		return host;
	}



	public Long getBindTime() {
		return bindTime;
	}

	public void setBindTime(Long bindTime) {
		this.bindTime = bindTime;
	}


	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	

	
	
	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	 
	public void setHost(String host) {
		this.host = host;
		 
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

 

	public boolean  isLocalhost()
	{
		
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			return ip.equals(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
		 
	}
	
	public boolean equals(Object o) {
        
		if (o instanceof CIMSession) {
			
			CIMSession t = (CIMSession) o;
			if(t.deviceId!=null && deviceId!=null)
			{
				return t.deviceId.equals(deviceId) && t.nid == nid  && t.host.equals(host);
			} 
		}  
		return false;
	}

    public boolean fromOtherDevice(Object o) {
        
		if (o instanceof CIMSession) {
			
			CIMSession t = (CIMSession) o;
			if(t.deviceId!=null && deviceId!=null)
			{
				return !t.deviceId.equals(deviceId);
			} 
		}  
		return false;
	}

    public boolean fromCurrentDevice(Object o) {
        
		return !fromOtherDevice(o);
	}
 
	
	
	
	public String  toString()
	{
		StringBuffer buffer = new   StringBuffer();
		buffer.append("{");
		
		buffer.append("\"").append("gid").append("\":").append("\"").append(gid).append("\"").append(",");
		buffer.append("\"").append("nid").append("\":").append(nid).append(",");
		buffer.append("\"").append("deviceId").append("\":").append("\"").append(deviceId).append("\"").append(",");
		buffer.append("\"").append("host").append("\":").append("\"").append(host).append("\"").append(",");
		buffer.append("\"").append("channel").append("\":").append("\"").append(channel).append("\"").append(",");
		buffer.append("\"").append("deviceModel").append("\":").append("\"").append(deviceModel).append("\"").append(",");
		buffer.append("\"").append("status").append("\":").append(status).append(",");
		buffer.append("\"").append("bindTime").append("\":").append(bindTime).append(",");
		buffer.append("}");
		return buffer.toString();
		
	}
}