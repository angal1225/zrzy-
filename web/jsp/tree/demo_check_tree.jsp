<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/jsp/common/include.jsp" %>
<HTML>
<HEAD>
	<TITLE>树</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	
	<SCRIPT type="text/javascript">
		<!--
			$(document).ready(function(){
				tree_check('treeDemo',${treeJson});
			});
		
			function onClick(){
				var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes = treeObj.getCheckedNodes(true);
			    var ids="";
			    var names="";
				for (x in nodes) {
					if($("#check_box").attr("checked")){
						if(!nodes[x].isParent){
							ids += nodes[x]["id"];
							names += nodes[x]["name"];
							if(x!=(nodes.length-1)){
					     		ids +=",";
					     		names +=",";
					    	}
					    }
					}else{
						ids += nodes[x]["id"];
						names += nodes[x]["name"];
						if(x!=(nodes.length-1)){
				     		ids +=",";
				     		names +=",";
				    	}
					}
			  	}
				  	
				window.opener.$('#'+getParameter("parameterId")).val(ids);
				window.opener.$('#'+getParameter("parameterName")).val(names);
				window.close();
				return;
			}

			function empty_data(){
				window.opener.$('#'+getParameter("parameterId")).val("");
				window.opener.$('#'+getParameter("parameterName")).val("");
				window.close();
				return;
			}
		//-->
	</SCRIPT>
 </HEAD>

<BODY>
<h1>加载节点数据的树</h1>
		<ul id="treeDemo" class="ztree"></ul>
<table>
	<tr>
		<td>
			<input type="checkbox" name="checkbox" id="check_box" />忽略父节点
		</td>
	</tr>
	<tr>
		<a href="#" id="btn" class="easyui-linkbutton" iconCls="icon-add" onclick="onClick()">确定</a>
		<a href="#" id="btn" class="easyui-linkbutton" iconCls="icon-cancel" onclick="empty_data()">清空</a>
	</tr>
</table>
</BODY>
</HTML>