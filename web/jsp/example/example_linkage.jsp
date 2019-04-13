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
		$("#test_type").on('change', function(){
			$.ajax({
				type: "POST",
				async:true,
				url: "linkage",
				data: {test_type:$(this).children('option:selected').val()},
				dataType: 'json',
				success: function(data){
					var select_obj = $('#test_dict');
					select_obj.empty();
					for(var i=0;i<data.length;i++){
						select_obj.append("<option value='"+data[i].value+"'>"+data[i].text+"</option>");
					}
				}
			});
		});
	});
</script>
</head>


<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form id="inForm" method="post" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10">
								<select id="test_type" name="test_type" class="form-control">
									<c:forEach items="${testTypeList}" var="testType">
										<option value="${testType.value}">${testType.text}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">字典</label>
							<div class="col-sm-10">
								<select id="test_dict" name="test_dict" class="form-control">
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
