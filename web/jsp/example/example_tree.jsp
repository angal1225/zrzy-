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
	$(function() {
		
			$.fn.zTree.init($('#functionTree'), {
				data: {
					simpleData: {
						enable: true
					}
				},
				view: {
					fontCss: getFontCss
				},
				callback: {
					onClick: function(event,treeId,treeNode){
						alert("点击事件，传入参数treeId："+treeId);
						alert("点击事件，传入参数event："+event);
						alert("点击事件，传入参数treeNode："+treeNode.id);
						alert("点击事件，传入参数treeNode："+treeNode.name);
					}
				}
			}, ${funcTreeJson});
			
			$.fn.zTree.init($('#functionTree2'), {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				view: {
					fontCss: getFontCss
				},
				callback: {
					onClick: function(event,treeId,treeNode){
						alert("点击事件，传入参数treeId："+treeId);
						alert("点击事件，传入参数event："+event);
						alert("点击事件，传入参数treeNode："+treeNode.id);
						alert("点击事件，传入参数treeNode："+treeNode.name);
					}
				}
			}, ${funcTreeJson});
			
			$(":button").on('click', function(){
				var treeObj = $.fn.zTree.getZTreeObj("functionTree2");
				var nodes = treeObj.getCheckedNodes(true);
				$(nodes).each(function(){
					alert(this.id + ";" +this.name);
				});
			});
	});
</script>
</head>


<body class="gray-bg">
	<div class="row">
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<div id="jstree1"></div>
					<ul id="functionTree" class="ztree"></ul>
				</div>
				<div class="ibox-content">
					<div id="jstree1"></div>
					<ul id="functionTree2" class="ztree"></ul>
				</div>
				<div class="ibox-content">
					<input type="button" value="选中值获取测试">
				</div>
			</div>
		</div>
	</div>
</body>
</html>
