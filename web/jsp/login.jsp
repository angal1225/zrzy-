<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
	
	$(function() {
		$("#inputForm").validate({
			rules : {
				login_id : "required",
				login_pd : "required"
			}
		});
	});
	
	function onEnterDown(){
	    if(window.event.keyCode == 13){
	    	login();
	    }
	}
	
	function login(){
		if($("#inputForm").valid()){
			$("#inputForm").attr('action',"<%=context_path%>/servlet/LoginServlet/loginCheck");
			$("#inputForm").submit();
		}
	}
</script>
</head>

<body class="gray-bg" onkeydown="onEnterDown();">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
				<div>
					<img src="/jsp/img/1.jpg">
				</div>
			<h3>欢迎来到erp信息管理系统</h3>

			<form class="m-t" role="form" method="post" id="inputForm">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="用户名" id="login_id" name ="login_id">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="密码" id="login_pd" name ="login_pd">
				</div>
				<button type="button" onclick="login()" class="btn btn-primary block full-width m-b">登录</button>

				<p class="text-muted text-center">
					<a href="javascript:void(0);" onclick="login()"> <small>忘记密码了？</small>
					</a> | <a href="<%=request.getContextPath()%>/jsp/sign.jsp">注册一个新账号</a>
				</p>
			</form>
		</div>
	</div>
</body>
</html>
