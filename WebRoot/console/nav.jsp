<%
	String navBasePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
%>
<%@ page language="java" pageEncoding="UTF-8"%>

<style>
 .btn-purple:hover,.btn-purple:active,.btn-purple:focus{
    background-color: #763DDB;
    background-image: none;
    border-color:#421198;
    color: white;
 }
 
  .btn-purple{
    background-color: #5F28C0;
    background-image: none;
    color: white;
    border-color:#421198;
 }
</style>
<div id="_main_nav" class="ui-vnav">
	<ul class="ui-nav-inner">
	
			<li  class="ui-item" id="sessionMenu">
				<a href="<%=navBasePath %>/console/session/list.action">
					<span class="glyphicon glyphicon-signal" style="top:2px;"></span>&nbsp;<span class="ui-text">在线设备</span>
				</a>
			</li>
			 
			<li  class="ui-item" id="messageMenu">
				<a href="<%=navBasePath %>/console/message/list.action">
					<span class="glyphicon glyphicon-comment" style="top:2px;"></span>&nbsp;<span class="ui-text">消息查询</span>
				</a>
			</li>
			<li  class="ui-item" id="serverMenu">
				<a href="<%=navBasePath %>/console/host/list.action">
					<span class="glyphicon glyphicon-cog" style="top:2px;"></span>&nbsp;<span class="ui-text">主机管理</span>
                </a>
			</li>
	</ul>
	 
</div>

