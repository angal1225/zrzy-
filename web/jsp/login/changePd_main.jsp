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
				old_passwd : {
					required : true,
					remote:{
                        url:"changePasswdCheck",
                        type:"post"
                    }
				},
				new_passwd : "required",
				confirm_passwd : {
					required : true,
					equalTo : '#new_passwd'
				}
			}
		});
	});
	
	function formSubmit(){
		if($("#inForm").valid()){
			$("#inForm").ajaxSubmit({  
                url: "changePasswdSave",  
                type: "post",  
                dataType: "json",
                resetForm: false,
                success: function(data, status, xhr) {
                	commitFormBack(data);
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
								<p class="form-control-static">${loginBean.login_id}</span></p>
								<input id="login_id" name="login_id" type="hidden" value="${loginBean.login_id}" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">原始密码</label>
							<div class="col-sm-10">
								<input id="old_passwd" name="old_passwd" type="password" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">更新密码</label>
							<div class="col-sm-10">
								<input id="new_passwd" name="new_passwd" type="password" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-10">
								<input id="confirm_passwd" name="confirm_passwd" type="password" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="button" onclick="formSubmit()">保存内容</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>