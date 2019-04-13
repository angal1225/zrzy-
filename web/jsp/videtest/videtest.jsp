<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>

<html>
<head>
<script type="text/javascript">
	var myPlayer;
	$(function(){
		videojs("my-video").ready(function() {
			myPlayer = this;
		});
		
		initTable();
	});
	

	function initTable(){
		initBootstrapTable({
	    	tableObj:$('#demo-table'),
	        url:"queryList",
	        uniqueId: "role_id",
	        singleSelect: false,
	        columns: [
                  		{field : 'code', title : '编码', align : 'center', valign : 'middle', sortable : true},
                  		{field : 'name', title : '名称', align : 'center', valign : 'middle', sortable : true},
                  		{field : 'op', title : '操作', align : 'center', valign : 'middle', 
                  			formatter : function (value, row, index){
	                	  		var detail = '<a href="#" onClick="changeVideo(\''+row.path+'\')"><span style="color:red;">播放</span></a>';
	                	  		return detail;
                  			}
                  		}
	                 ]
	    });
	}
	
	function changeVideo(vdoSrc){
		myPlayer.src(vdoSrc);
	    myPlayer.load();
	    myPlayer.play();
	}
	
	
</script>
</head>


<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>视频列表</h5>
					</div>
					<div class="ibox-content">
						<table id="demo-table" class="table table-bordered table-hover table-striped" style="word-break:break-all; word-wrap:break-all;">
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>视频播放</h5>
					</div>
					<div class="ibox-content">
						<video id="my-video" class="video-js" controls width="960"></video>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>