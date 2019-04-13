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
		$("#inForm").validate({
			rules : {
				role_id : {
					required : true,
					remote:{
                        url:"checkRoleId",
                        type:"post"
                    }
				},
				role_name : "required"
			}
		});
	});
	
	function formSubmit(){
		if($("#inForm").valid()){
			$("#inForm").ajaxSubmit({  
                url: "upSave",  
                type: "post",  
                dataType: "json",
                resetForm: false,
                success: function(data, status, xhr) {
                	commitFormBackIform(data);
                }
        	});
		}
	}
</script>
</head>


<body class="gray-bg">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-content">
					<form id="inForm" method="post" class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">角色编码</label>
							<div class="col-sm-10">
								<p class="form-control-static">${roleMap.role_id}</span></p>
								<input id="role_id" name="role_id" type="hidden" value="${roleMap.role_id}" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">角色名称</label>
							<div class="col-sm-10">
								<input id="role_name" name="role_name" value="${roleMap.role_name}" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">账户状态</label>
							<div class="col-sm-10">
								<select id="role_status" name="role_status" class="form-control">
									<c:forEach items="${statusList}" var="status">
										<c:choose>
											<c:when test="${status.value == roleMap.role_status}">
												<option value="${status.value}" selected>${status.text}</option>
											</c:when>
											<c:otherwise>
												<option value="${status.value}">${status.text}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="button" onclick="formSubmit()">保存内容</button>
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
