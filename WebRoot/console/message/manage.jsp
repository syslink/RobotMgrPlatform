<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/table.tld" prefix="table"%>
<%@ taglib uri="/WEB-INF/tld/function.tld" prefix="function"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>RMP管理系统</title>
	    <%@include file="/resources.jsp"%>
		<script>
		  function doDelete(id)
		  {
		     var setting = {hint:"删除后无法恢复,确定删除这个消息吗?",
		                    onConfirm:function(){
		                      showProcess('正在删除，请稍候......');
		                      $.post("<%=basePath%>/console/message/delete.action", {mid:id},
							  function(data){
							      hideProcess();
							      showSTip("删除成功");
					              $('#'+id).fadeOut().fadeIn().fadeOut();
					              doHideConfirm();
						      });
		                     
		                    }};
		     doShowConfirm(setting);
		  }
		  $(function () {
             $('[data-toggle="popover"]').popover();
          });
		</script>
	</head>
	<body class="web-app ui-selectable">


		<%@include file="../header.jsp"%>

		<%@include file="../nav.jsp"%>

		<div id="mainWrapper">
		<div class="lay-main-toolbar">
           </div>
		 
				<div>
					<form action="<%=basePath%>/console/message/list.action" method="post"
						id="searchForm" style="padding: 0px;">
						<input type="hidden" name="currentPage" id="currentPage" value="1"/>
						<table width="100%" class="utable">

							<thead>
								<tr class="tableHeader">
								    <th width="16%">ID</th>
									<th width="17%">接收设备</th>
									<th width="6%">状态</th>
									<th width="6%">类型</th>
									<th width="40%">内容</th>
									<th width="10%">发送时间</th>
									<th width="5%">操作</th>
								</tr>
							 	<tr>
                                    <td>
                                      <input name="mid" type="text" value="${message.mid }"
											class="form-control"
											style="margin: 3px 0px;height: 32px;" /> 
									</td>

									<td>
										<input name="receiver" type="text" value="${message.receiver }"
											class="form-control"
											style="margin: 3px 0px;height: 32px;" />
									</td>
									
									<td>
									<select name="state" id="mstatus" class="form-control" style="width: 100%; margin: 3px 0px;height: 32px;">
									 <option />
									 <option value="2" <c:if test="${message.state eq '2'}"> selected="selected"</c:if>>已阅读</option>
									 <option value="1" <c:if test="${message.state eq '1'}"> selected="selected"</c:if>>已接收</option>
									 <option value="0" <c:if test="${message.state eq '0'}"> selected="selected"</c:if>>未接收</option>
									</select>
									</td>
									<td>
										<input name="action" type="text" value="${message.action }"
											class="form-control"
											style="margin: 3px 0px;height: 32px;"/>
									</td>
									<td>
									</td>
									<td>
									</td>
									<td >
										<button type="submit" class="btn btn-primary btn-sm">
											<span class="glyphicon glyphicon-search"></span> 查询
										</button>
									</td>
								</tr>
							 
							</thead>
							<tbody id="checkPlanList">

								<c:forEach var="message" items="${page.dataList}">
									<tr id="${message.mid}"  style=" height: 50px;">
										<td>
										 
										<span  tabindex="0" role="button" data-toggle="popover" title="显示" data-placement="top" data-content="${message.mid }">${message.mid }</span>
										</td>
										<td>
											${message.receiver }
										</td>
										<td>
										    <c:if test="${message.state eq '2'}">
										          已阅读
										    </c:if>
										    <c:if test="${message.state eq '1'}">
										         已接收
										    </c:if>
											 <c:if test="${message.state eq '0'}">
										         未接收
										    </c:if>
										</td>
										<td>
											${message.action }
										</td>
										<td>
										   <div style="overflow: hidden;text-overflow: ellipsis;line-height:49px;padding:0px 5px; " tabindex="0" role="button"    data-toggle="popover" title="详情" data-placement="left" data-content="${function:html(message.content)}">${function:html(message.content)}</div>
										</td>
									    <td><table:datetime timestamp="${message.timestamp}"></table:datetime></td>
										<td>
									     	<div class="btn-group btn-group-xs">
										 	  <button type="button" class="btn btn-danger"  style="padding: 5px;" onclick="doDelete('${message.mid}')">
										 	  <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除
										 	  </button>
											</div>
											
										</td>
									</tr>
								</c:forEach>
								 
							</tbody>
							<tfoot>
							<tr>
									<td colspan="6">
										<table:page page="${page}"></table:page>
									</td>
								</tr>
							
							</tfoot>
						</table>
					</form>

				</div>
			</div>

		<script>
		       $('#messageMenu').addClass('current');
		</script>
	</body>
</html>
