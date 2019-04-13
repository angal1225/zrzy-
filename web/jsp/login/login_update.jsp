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
				login_id : {
					required : true,
					remote:{
                        url:"checkLoginId",
                        type:"post"
                    }
				},
				login_name : "required",
				login_role : "required",
				login_status : "required"
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
							<label class="col-sm-2 control-label">登录账号</label>
							<div class="col-sm-10">
								<p class="form-control-static">${loginMap.login_id}</span></p>
								<input id="login_id" name="login_id" type="hidden" value="${loginMap.login_id}" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">账号姓名</label>
							<div class="col-sm-10">
								<input id="login_name" name="login_name" type="text" value="${loginMap.login_name}" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">账号权限</label>
							<div class="col-sm-10">
								<select id="login_role" name="login_role" class="form-control">
									<c:forEach items="${roleDictList}" var="roleDict">
										<c:choose>
											<c:when test="${roleDict.value == loginMap.login_role}">
												<option value="${roleDict.value}" selected>${roleDict.text}</option>
											</c:when>
											<c:otherwise>
												<option value="${roleDict.value}">${roleDict.text}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">账户状态</label>
							<div class="col-sm-10">
								<select id="login_status" name="login_status" class="form-control">
									<c:forEach items="${statusList}" var="status">
										<c:choose>
											<c:when test="${status.value == loginMap.login_status}">
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
							<label class="col-sm-2 control-label">人员信息</label>
							<div class="col-sm-10">
							
								<input id="people_name" name="people_name" type="text" value="${loginMap.people_name}" onClick="peopleOnClick('people_code', 'people_name')" class="form-control" />
								<input id="people_code" name="people_code" type="hidden" value="${loginMap.people_code}" class="form-control" />
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
