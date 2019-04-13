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
	function test(){
		$("#inForm").attr("action", "/zrzy/fdsasdf/fdsasdf/upload");
		$("#inForm").submit();
	}
</script>
</head>


<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form id="inForm" method="post" class="form-horizontal" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-sm-2 control-label">测试上传</label>
							<div class="col-sm-10">
								<input id="people_test_file" name="people_test_file" type="file" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">人员编码</label>
							<div class="col-sm-10">
								<input id="people_id" name="people_id" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="button" onclick="test()">保存内容</button>
								<button class="btn btn-white" type="button" onclick="closeIform()">关闭</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
