<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<script type="text/javascript">
		$(function () {
			initTable();
		});
		
		function initTable(){
			initBootstrapTable({
		    	tableObj:$('#demo-table'),
		        url:"roleQueryList",
		        uniqueId: "role_id",
		        singleSelect: false,
		        columns: [
		                  	{checkbox: true},
	                  		{field : 'role_id', title : '角色编码', align : 'center', valign : 'middle', sortable : true},
	                  		{field : 'role_name', title : '角色名称', align : 'center', valign : 'middle', sortable : true},
	                  		{field : 'status_name', title : '角色状态', align : 'center', valign : 'middle'},
	                  		{field : 'op', title : '操作', align : 'center', valign : 'middle', 
	                  			formatter : function (value, row, index){
		                	  		var detail = '';
		                	  		detail = detail + '<a href="#" onClick="editOnClick(\''+row.role_id+'\')"><span style="color:red;">编辑</span></a>';
		                	  		detail = detail + '&nbsp;<a href="#" onClick="roleClick(\''+row.role_id+'\')"><span style="color:red;">赋权</span></a>';
		                	  		return detail;
	                  			}
	                  		}
		                 ]
		    });
		}
		
		function editOnClick(code){
			layer.open({
		    	  type: 2,
		    	  title: '修改页面',
		    	  shadeClose: true,
		    	  area: ['80%', '100%'],
		    	  skin: 'layui-layer-rim',
		    	  content: 'upMain?role_id=' + code
		    }); 
		}
		
		function addSave(){
			layer.open({
		    	  type: 2,
		    	  title: '新增页面',
		    	  shadeClose: true,
		    	  area: ['80%', '100%'],
		    	  skin: 'layui-layer-rim',
		    	  content: 'addMain'
		    }); 
		}

		function roleClick(code){
			layer.open({
		    	  type: 2,
		    	  title: '赋权页面',
		    	  shadeClose: true,
		    	  area: ['80%', '100%'],
		    	  skin: 'layui-layer-rim',
		    	  content: 'funcTree?role_id=' + code
		    }); 
		}
		
		function deleteOnClick(){
			var ckString = selectBootstrapTable($('#demo-table'), 'role_id');
        	if(ckString == null || ckString == undefined || ckString == '') {
        		layer.open({
        			content: '请选择删除项'
        		});
        		return;
        	}
        	
        	layer.confirm('是否删除选中数据？', function(){
       			$('#ck_sign').val(ckString);
   				$("#toolbar").attr('action',"deleteSave");
   				$("#toolbar").submit();
       		});
		}
		
	</script>
</head>


<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="example-wrap">
							<h4 class="example-title">角色管理</h4>
							<div>
								<form class="form-inline" style="float: left; width: 100%" method="post" id="toolbar">
									<input type="hidden" name="ck_sign" id="ck_sign"/>
									<div class="form-group">
										<label>角色名称:</label>
										<input type="text" class="form-control" name="role_name" id="role_name" />
									</div>
									<div class="form-group">
										<label>角色状态:</label>
										<select id="role_status" name="role_status" class="form-control">
											<c:forEach items="${statusList}" var="status">
												<option value="${status.value}">${status.text}</option>
											</c:forEach>
										</select>
									</div>
                        
									<div class="form-group">
										<button type="button" id="queryBtn" onclick="doQuery($('#demo-table'));" class="btn btn-primary">查询</button>
									</div>
									<div class="form-group btn-right">
										<button type="button" class="btn btn-primary" id="addBtn" onclick="addSave();">新增</button>
									</div>
									<div class="form-group btn-right">
										<button type="button" class="btn btn-primary" id="addBtn" onclick="deleteOnClick();">删除</button>
									</div>
								</form>
							</div>
							<div class="example">
								<table id="demo-table" class="table table-bordered table-hover table-striped" style="word-break:break-all; word-wrap:break-all;">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
