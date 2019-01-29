    <%@ page language="java" pageEncoding="UTF-8"%>

<%
	String headerBasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<script type="text/javascript">
   
    function doLogin()
	{
		    var account = $('#account').val();
		    var password = $('#password').val();
		    if($.trim(account)=='' || $.trim(password)=='')
		    {
		       return;
		    }
		    
		    showProcess('正在登录请稍候......');
		    $.post("<%=headerBasePath%>/system/login.do", {account:account,password:password},
			   function(data){
			      hideProcess();
			      if(data == 403)
			      {
			         showETip("账号或者密码错误");
			         return ;
			      }
			      doHideDialog('LoginDialog');
			      $("#loginButton").text("系统管理员");
			      $("#loginButton").removeAttr("onclick");
		     });
		}
	function doModifyAdminPassrod()
	{
		    var curPasswrod = $('#curPasswrod').val();
		    var newPasswrod = $('#newPasswrod').val();
		    var cfmPasswrod = $('#cfmPasswrod').val();
		    if($.trim(cfmPasswrod)=='' || $.trim(newPasswrod)=='' || $.trim(curPasswrod)=='')
		    {
		       return;
		    }
		    if(cfmPasswrod!=newPasswrod)
		    {
		       showETip("确认密码不一致");
		       return;
		    }
		    showProcess('正在保存请稍候......');
		    $.post("<%=headerBasePath%>/system/manager/modifyPassword.action", {oldPassword:curPasswrod,newPassword:newPasswrod},
			   function(data){
			      hideProcess();
			      if(data == 403)
			      {
			         showETip("当前密码错误");
			         return ;
			      }
			      if(data == 404)
			      {
			         window.location.href="<%=headerBasePath%>/login.jsp";
			         return ;
			      }
			      if(data == 200)
			      {
			         showSTip("密码修改成功");
			         doHideDialog('ModifyPasswordDialog');
			      }
		     });
		}	  
</script>


<div id="_main_fixed_header" class="header-fixed">

	<!-- 头部 -->
	<div id="_main_header_banner" class="header">
		<div id="_main_header_cnt" style="margin-left: 220px;margin-right: 20px;">
			<div class="logo" style="left: -200px;padding-top: 10px;position: relative;">
			 <img src="<%=headerBasePath%>/resource/img/top_logo.png"/>
			</div>

		</div>

	</div>

	<!--web的导航在左侧-->

</div>
 


<div id="global_mask" style="display: none; position: absolute; top: 0px; left: 0px; z-index: 9999; background-color: rgb(20, 20, 20); opacity: 0.5; width: 100%; height: 100%; overflow: hidden; background-position: initial initial; background-repeat: initial initial;"></div>
