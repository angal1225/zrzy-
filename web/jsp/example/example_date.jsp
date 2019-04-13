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
		$("#example_ymd").datetimepicker({
	    	language:  'zh-CN',
	    	format: 'yyyy-mm-dd',
	    	startView: 'month',
	    	minView: 'month',
	    	maxView: 'decade',
	    	autoclose: false,
	    	todayBtn: 'linked',
	    	weekStart: 0,
			todayHighlight: true,
			forceParse: false,
	        showMeridian: true
	    });
		
		$("#example_ym").datetimepicker({
	    	language:  'zh-CN',
	    	format: 'yyyy-mm',
	    	startView: 'year',
	    	minView: 'year',
	    	maxView: 'decade',
	    	autoclose: false,
	    	todayBtn: 'linked',
	    	weekStart: 0,
			todayHighlight: true,
			forceParse: false,
	        showMeridian: true
	    });
		
		$("#example_y").datetimepicker({
		  	language:  'zh-CN',
		  	format: 'yyyy',
		  	startView: 'decade',
		  	minView: 'decade',
		  	maxView: 'decade',
		  	autoclose: false,
		  	todayBtn: 'linked',
		  	weekStart: 0,
			todayHighlight: true,
			forceParse: false,
		    showMeridian: true
		  });
		
		$("#example_yyyymmddhhiiss").datetimepicker({
	    	language:  'zh-CN',
	    	format: 'yyyy-mm-dd hh:ii:ss',
	    	startView: 'month',
	    	minView: 'hour',
	    	maxView: 'decade',
	    	autoclose: false,
	    	todayBtn: 'linked',
	    	weekStart: 0,
			todayHighlight: true,
			forceParse: false,
	        showMeridian: true
	    });
		
		$("#example_hhiiss").datetimepicker({
	    	language:  'zh-CN',
	    	format: 'hh:ii:ss',
	    	startView: 'hour',
	    	minView: 'hour',
	    	maxView: 'hour',
	    	autoclose: false,
	    	todayBtn: 'linked',
	    	weekStart: 0,
			todayHighlight: true,
			forceParse: false,
	        showMeridian: true
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
							<label class="col-sm-2 control-label">日期</label>
							<div id="example_ymd" class="col-sm-10 input-group date form_time" >
                 				<input id="birth_date" name="birth_date" class="form-control" type="text" value='${peopleBean.birth_date }' >
                 				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
               				</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">月份</label>
							<div id="example_ym" class="col-sm-10 input-group date form_time" >
                 				<input id="birth_date" name="birth_date" class="form-control" type="text" value='${peopleBean.birth_date }' >
                 				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
               				</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">年度</label>
							<div id="example_y" class="col-sm-10 input-group date form_time" >
                 				<input id="birth_date" name="birth_date" class="form-control" type="text" value='${peopleBean.birth_date }' >
                 				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
               				</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">年月日时分秒</label>
							<div id="example_yyyymmddhhiiss" class="col-sm-10 input-group date form_time" >
                 				<input id="birth_date" name="birth_date" class="form-control" type="text" value='${peopleBean.birth_date }' >
                 				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
               				</div>
						</div>
						<div class="hr-line-dashed"></div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">时分秒</label>
							<div id="example_hhiiss" class="col-sm-10 input-group date form_time" >
                 				<input id="birth_date" name="birth_date" class="form-control" type="text" value='${peopleBean.birth_date }' >
                 				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
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
