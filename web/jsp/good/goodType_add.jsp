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
	
	$(function(){
		$("#inForm").validate({
			rules : {
				people_id : {
					required : true,
					remote:{
                        url:"/zrzy/servlet/PeopleServlet/checkPeopleId",
                        type:"post"
                    }
				},
				people_name : "required",
				people_sex : "required",
				people_age : {
					required : true,
					number : true
				},
				people_phone : {
					required : true,
					isMobile : true
				},
				people_card : {
					isIdCardNo : true
				}
			},
			messages : {
				people_id : {
					remote:"id冲突，请修正"
				},
				people_age : "年龄必填，不能隐瞒、瞎写",
				people_phone : {
					required : "电话必填",
					isMobile : "格式不对！"
				}
			}
		});
	});


	function test(){
		if($("#inForm").valid()){
			$("#inForm").ajaxSubmit({
				url:"addSave",
				type:"post",
				success:function(data){
					if(data.returnValue=='true' || data.returnValue == true){
						layer.open({
							content: data.returnInfo,
							end: function(index, layero){
								parent.window.location.reload();
							}
						});
					}else{
						layer.alert(data.returnInfo);
					}
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
							<label class="col-sm-2 control-label">人员编码</label>
							<div class="col-sm-10">
								<input id="people_id" name="people_id" type="text" class="form-control">
								<span class="help-block m-b-none">登录账号，不能重复</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">人员姓名</label>
							<div class="col-sm-10">
								<input id="people_name" name="people_name" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-sm-2 control-label">人员性别</label>
							<div class="col-sm-10">
								<select id="people_sex" name="people_sex" class="form-control">
									<c:forEach items="${sexList}" var="sexMap">
										<option value='${sexMap.id }'>${sexMap.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">年龄</label>
							<div class="col-sm-10">
								<input id="people_age" name="people_age" type="number" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-10">
								<input id="people_phone" name="people_phone" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">身份证</label>
							<div class="col-sm-10">
								<input id="people_card" name="people_card" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">地址</label>
							<div class="col-sm-10">
								<input id="people_address" name="people_address" type="text" class="form-control">
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
