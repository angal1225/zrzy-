<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/jsp/common/include.jsp"%>
<HTML>
<HEAD>
	<TITLE>树</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	
	<SCRIPT type="text/javascript">
		<!--
			$(document).ready(function(){
				tree('treeDemo',${treeJson});
			});
		
			function onClick(event,treeId,treeNode){
				$(window.parent.document).find('#'+getParameter("parameterId")).val(treeNode.id);
				$(window.parent.document).find('#'+getParameter("parameterName")).val(treeNode.name);
				closeIform();
				return;
			}

			function empty_data(){
				$(window.parent.document).find('#'+getParameter("parameterId")).val("");
				$(window.parent.document).find('#'+getParameter("parameterName")).val("");
				closeIform();
				return;
			}

		//-->
	</SCRIPT>
 </HEAD>
<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form id="inForm" method="post" class="form-horizontal">
						<div class="form-group">
							<div class="col-sm-10">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="button" onclick="empty_data()">清空</button>
								<button class="btn btn-white" type="button" onclick="closeIform()">关闭</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</HTML>