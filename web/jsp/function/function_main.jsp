<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>


<link href="<%=context_path%>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="<%=context_path%>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="<%=context_path%>/js/plugins/jsTree/themes/default/style.min.css" rel="stylesheet">
<link href="<%=context_path%>/css/animate.min.css" rel="stylesheet">
<link href="<%=context_path%>/css/style.min862f.css?v=4.1.0" rel="stylesheet">

<script src="<%=context_path%>/js/content.min.js?v=1.0.0"></script>
<script src="<%=context_path%>/js/plugins/jsTree/jstree.min.js"></script>


<html>
<head>
<script type="text/javascript">
		$(function(){
			$("#inForm").validate({
				rules : {
					func_id : {
						required : true,
						remote:{
	                        url:"checkLoginId",
	                        type:"post"
	                    }
					},
					func_name : "required"
				}
			});
			
			tree('functionTree',${funcTreeJson});
		});
		
		function onClick(event,treeId,treeNode){
			var url = "functionInfo";
			var map = new Map();
			map.put("func_id",treeNode.id);
			ajax_json(map, url, 'ajaxback');
		}
		function ajaxback(data){
			var objs=eval(data);
			if(objs!=null){
				$("#view_func_id").html(objs.func_id);
				$("#func_id").val(objs.func_id);
				$("#func_name").val(objs.func_name);
				$("#func_url").val(objs.func_url);
				$("#show_order").val(objs.show_order);
				$("#func_status").val(objs.func_status); 
				$("#note").val(objs.note);
			}
		}

		function saveUpdate(){
			var treeObj = $.fn.zTree.getZTreeObj("functionTree");
			var nodes = treeObj.getSelectedNodes();
			var node = nodes[0];
			if(node==null || node.length==0){
				layer.alert("请先选择一个节点");
			}
			if($("#inForm").valid()){
				var parameter = $("#inForm").serializeObject();
				$.ajax({
					type: "POST",
					async:true,
					url: 'saveUpdate',
					data: parameter,
					dataType: 'json',
					success: function(data){
						updateCallback(data);
					}
				});
			}
		}
		
		function updateCallback(data){
			if(data == "false"){
				layer.alert("操作失败");
			}else{
				var treeObj = $.fn.zTree.getZTreeObj("functionTree");
				var nodes = treeObj.getSelectedNodes();
				var node = nodes[0];
				var parentNode = node.getParentNode();
				node.name=$("#func_name").val();
				treeObj.removeNode(node);
				treeObj.addNodes(parentNode, eval(data));
				layer.alert("操作成功");
			}
		}
		
		function saveDelete(){
			var treeObj = $.fn.zTree.getZTreeObj("functionTree");
			var nodes = treeObj.transformToArray(treeObj.getNodes());
			if(nodes.length < 2){
				layer.alert("节点为根节点，不能删除");
				return;
			}
			nodes = treeObj.getSelectedNodes();
			var node = nodes[0];
			if(node!=null && node.length!=0){
				var znodes = treeObj.transformToArray(node);
				if(znodes!=null && znodes.length>1){
					layer.alert("此节点有子节点，不允许删除");
				}else{
		        	layer.confirm('确认删除此节点吗？', function(){
		        		var map = new Map();
						map.put("func_id",node.id);
						var url = "saveDelete";
						ajax_json(map, url,'deleteCallback');
		       		});
		        	
				}
			}else{
				layer.alert("请先选择一个节点");
			}
		}
		function deleteCallback(data){
			if(data.returnValue=="true"){
				var treeObj = $.fn.zTree.getZTreeObj("functionTree");
				var nodes = treeObj.getSelectedNodes();
				var node = nodes[0];
				treeObj.removeNode(node);
				clearInfo();
			}
			layer.alert(data.returnInfo);
		}

		function addOnClick(){
			var treeObj = $.fn.zTree.getZTreeObj("functionTree");
			var nodes = treeObj.getSelectedNodes();
			var node = nodes[0];
			if(node==null || node.length==0){
				layer.alert("请先选择一个节点");
				return;
			}
			
			layer.open({
		    	  type: 2,
		    	  title: '新增页面',
		    	  shadeClose: true,
		    	  area: ['80%', '100%'],
		    	  skin: 'layui-layer-rim',
		    	  content: 'addMain'
		    }); 
		}
		
		function clearInfo(){
			$("#view_func_id").html("");
			$("#func_id").val("");
			$("#func_name").val("");
			$("#func_url").val("");
			$("#show_order").val("");
			$("#func_status").val("");
			$("#note").val("");
		}
		
	</script>
</head>


<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							功能展示
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> <a
								class="close-link"> <i class="fa fa-times"></i> </a>
						</div>
					</div>
					<div class="ibox-content">
						<div id="jstree1">
                        </div>
						<ul id="functionTree" class="ztree"></ul>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>功能编辑</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i> </a> <a
								class="close-link"> <i class="fa fa-times"></i> </a>
						</div>
					</div>
					
					
					<div class="ibox-content">
						<form id="inForm" method="post" class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">功能编码</label>
								<div class="col-sm-10">
									<p class="form-control-static"><span id="view_func_id"></span></p>
									<input id="func_id" name="func_id" type="hidden" class="form-control">
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
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="button" onclick="saveUpdate()">保存变更</button>
									<button class="btn btn-primary" type="button" onclick="addOnClick()">新增节点</button>
									<button class="btn btn-white" type="button" onclick="saveDelete()">删除</button>
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