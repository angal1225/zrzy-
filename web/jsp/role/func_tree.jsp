<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/include.jsp"%>

<HTML>
<HEAD>
<TITLE>树</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<SCRIPT type="text/javascript">
			$(document).ready(function(){
				tree_check('treeDemo',${funcTree});
			});
		
			function onClick(){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes = treeObj.getCheckedNodes(true);
			    var ids="";
			    var names="";
				for (x in nodes) {
					ids += nodes[x]["id"];
					names += nodes[x]["name"];
					if(x!=(nodes.length-1)){
			     		ids +=";";
			     		names +=",";
			    	}
			  	}

				$('#ck_sign').val(ids);
				
				$("#inForm").ajaxSubmit({  
	                url: "saveRoleFunc",  
	                type: "post",  
	                dataType: "json",
	                resetForm: false,
	                success: function(data, status, xhr) {
	                	commitFormBackIform(data);
	                }
	        	});
			}

		</SCRIPT>
</HEAD>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<div class="col-sm-12">
						<div class="example-wrap">
							<h4 class="example-title">角色管理</h4>
							<div class="example">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
							<div>
								<form class="form-inline" style="float: left; width: 100%" method="post" id="inForm">
									<input name="ck_sign" id="ck_sign" type="hidden"/>
									<input id='role_id' name='role_id' value="${role_id}" type="hidden"/>
                        
									<div class="form-group">
										<button type="button" id="queryBtn" onclick="onClick();" class="btn btn-primary">确定</button>
									</div>
									<div class="form-group btn-right">
										<button class="btn btn-white" type="button" onclick="closeIform()">关闭</button>
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
</HTML>