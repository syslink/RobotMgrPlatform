
<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript">
	<%
	   String addBasePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
    %>
     function doAdd()
	{
		    var ip = $('#Aip').val();
		    var descrption = $('#Adescrption').val();
		    if($.trim(ip)=='')
		    {
		       return;
		    }
		    
		    showProcess('正在保存，请稍候......');
		    $.post("<%=addBasePath%>/console/host/add.action", {ip:ip,descrption:descrption},
			   function(data){
			   
			      showSTip("添加成功");
			      doHideDialog('AddDialog');
			      window.location.href=$('#searchForm').attr('action');
			      
		     });
		}
		  
   </script>

<div class="modal fade" id="AddDialog" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabele2">
	<div class="modal-dialog" style="width: 320px;" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabele2">
					添加配置
				</h4>
			</div>
			<div class="modal-body">



				<form role="form">
					<div class="form-group">
						<label style="width: 50px;">
							<font color="red">*</font>IP:
						</label>
						<input type="text" class="form-control" id="Aip" maxlength="15"
							style="display: inline; width: 220px;" />
					</div>
					<div class="form-group" style="margin-top: 20px;">
						<label style="width: 50px;">
							<font color="red"></font>说明:
						</label>
						<input type="text" class="form-control" id="Adescrption"
							maxlength="32" style="display: inline; width: 220px;" />
					</div>
				</form>
			</div>
			<div class="modal-footer"
				style="padding: 5px 10px; text-align: center;">
				<a type="button" class="btn btn-success btn-lg" onclick="doAdd()"
					style="width: 200px;"> 保 存</a>
			</div>
		</div>
	</div>
</div>