<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>

<html>
<head>
<script type="text/javascript">

		$(function(){
			$("#inForm").validate({
				rules : {
					func_id : {
						required : true,
						remote:{
	                        url:"checkCode",
	                        type:"post"
	                    }
					},
					func_name : "required",
					func_status : "required"
				}
			});
		});

		var opType = "0";
		function saveUpdate(type){
			opType = type;
			var treeObj = window.parent.$.fn.zTree.getZTreeObj("functionTree");
			var nodes = treeObj.getSelectedNodes();
			var node = nodes[0];

			if(node==null || node.length==0){
				layer.alert("请先选择一个节点");
			}
			
			if(opType=="0"){
				$("#parent_id").val(node.id);
			}else if(opType=="1"){
				var parentNode = node.getParentNode();
				if(parentNode == null){
					$("#parent_id").val("0000");
				}else{
					$("#parent_id").val(parentNode.id);
				}
			}
			
			if($("#inForm").valid()){
				var parameter = $("#inForm").serializeObject();
				$.ajax({
					type: "POST",
					async:true,
					url: 'saveAdd',
					data: parameter,
					dataType: 'json',
					success: function(data){
						addCallback(data);
					}
				});
			}
		}
		
		function addCallback(data){
			if(data == "false"){
				layer.alert("操作失败");
			}else{
				var treeObj = window.parent.$.fn.zTree.getZTreeObj("functionTree");
				var nodes = treeObj.getSelectedNodes();
				var node = nodes[0];
				var parentNode;
				if(opType=="0"){
					parentNode = node;
				}else if(opType=="1"){
					parentNode = node.getParentNode();
				}
			 	var newNodes = treeObj.addNodes(parentNode, eval(data));
				layer.open({
					content: "操作成功",
					end: function(index, layero){
						closeIform();
					}
				});
			}
		}
		
		function clearInfo(){
			$("#func_id").val("");
			$("#func_name").val("");
			$("#func_url").val("");
			$("#func_status").val("");
			$("#note").val("");
		}
		
	</script>

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form id="inForm" method="post" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">功能编码</label>
								<div class="col-sm-10">
									<input id="func_id" name="func_id" type="text" class="form-control">
									<input id="parent_id" name="parent_id" value="" type="hidden" />
									<span class="help-block m-b-none">功能编码，不能重复</span>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
	
							<div class="form-group">
								<label class="col-sm-2 control-label">功能名称</label>
								<div class="col-sm-10">
									<input id="func_name" name="func_name" type="text" class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
	
							<div class="form-group">
								<label class="col-sm-2 control-label">访问地址</label>
								<div class="col-sm-10">
									<input id="func_url" name="func_url" type="text" class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
	
							<div class="form-group">
								<label class="col-sm-2 control-label">显示排序</label>
								<div class="col-sm-10">
									<input id="show_order" name="show_order" type="number" class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
	
							<div class="form-group">
								<label class="col-sm-2 control-label">是否激活</label>
								<div class="col-sm-10">
									<select id="func_status" name="func_status" class="form-control">
										<c:forEach items="${statusList}" var="status">
											<option value="${status.value}">${status.text}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
	
							<div class="form-group">
								<label class="col-sm-2 control-label">备注</label>
								<div class="col-sm-10">
									<input id="note" name="note" type="text" class="form-control">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
	
							<div class="form-group">
								<div class="col-sm-6 col-sm-offset-2">
									<button class="btn btn-primary" type="button" onclick="saveUpdate('1')">新增同级</button>
									<button class="btn btn-primary" type="button" onclick="saveUpdate('0')">新增下级</button>
									<button class="btn btn-white" type="button" onclick="clearInfo()">清空</button>
									<button class="btn btn-white" type="button" onclick="closeIform()">关闭</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>