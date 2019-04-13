<%@page import="com.formwork.base.bean.ReturnBean"%>
<%
	response.setDateHeader ("Expires", 0);
	String context_path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
%>

<%@ page contentType="text/html;charset=UTF-8" %>


 
<link href="<%=context_path%>/css/bootstrap.min14ed.css?v=3.3.6"
	rel="stylesheet">
<link href="<%=context_path%>/css/font-awesome.min93e3.css?v=4.4.0"
	rel="stylesheet">
<link
	href="<%=context_path%>/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<link href="<%=context_path%>/css/animate.min.css" rel="stylesheet">
<link href="<%=context_path%>/css/style.min862f.css?v=4.1.0"
	rel="stylesheet">

<link href="<%=context_path%>/css/plugins/iCheck/custom.css"
	rel="stylesheet">

<link href="<%=context_path%>/css/plugins/chosen/chosen.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/css/plugins/colorpicker/css/bootstrap-colorpicker.min.css"
	rel="stylesheet">
<link href="<%=context_path%>/css/plugins/cropper/cropper.min.css"
	rel="stylesheet">
<link href="<%=context_path%>/css/plugins/switchery/switchery.css"
	rel="stylesheet">
<link href="<%=context_path%>/css/plugins/jasny/jasny-bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/css/plugins/nouslider/jquery.nouislider.css"
	rel="stylesheet">
<link href="<%=context_path%>/css/plugins/datapicker/datepicker3.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/css/plugins/ionRangeSlider/ion.rangeSlider.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/css/plugins/ionRangeSlider/ion.rangeSlider.skinFlat.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
	rel="stylesheet">
<link href="<%=context_path%>/css/plugins/clockpicker/clockpicker.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/js/plugins/bootstrap-table/bootstrap-table.css"
	rel="stylesheet">
<link
	href="<%=context_path%>/js/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<link href="<%=context_path%>/js/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">

<link href="<%=context_path%>/js/plugins/jsTree/themes/default/style.min.css" rel="stylesheet">

<link href="<%=context_path%>/js/plugins/image-picker/image-picker.css" rel="stylesheet">

<link href="<%=context_path%>/js/plugins/fileinput/css/fileinput.css" media="all" rel="stylesheet">
<link href="<%=context_path%>/js/plugins/webuploader-0.1.5/webuploader.css" media="all" rel="stylesheet">
<link href="<%=context_path%>/css/demo/webuploader-demo.min.css" media="all" rel="stylesheet">

<link href="<%=context_path%>/js/vide7.1.0/css/video-js.min.css" rel="stylesheet">

 
<script type="text/javascript"
	src="<%=context_path%>/js/jquery.min.js?v=2.1.4"></script>
 <script type="text/javascript"
	src="<%=context_path%>/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/json2.js" ></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/jsKnob/jquery.knob.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/jasny/jasny-bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/prettyfile/bootstrap-prettyfile.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/nouslider/jquery.nouislider.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/switchery/switchery.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/ionRangeSlider/ion.rangeSlider.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/clockpicker/clockpicker.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/cropper/cropper.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/demo/form-advanced-demo.min.js"></script>

<script type="text/javascript"
	src="<%=context_path%>/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/bootstrap.min.js?v=3.3.6"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/content.min.js?v=1.0.0"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/demo/bootstrap-table-demo.min.js"></script>

<script type="text/javascript"
	src="<%=context_path%>/js/plugins/iCheck/icheck.min.js"></script>

<script type="text/javascript"
	src="<%=context_path%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/layer/layer.js"></script>

<script type="text/javascript"
	src="<%=context_path%>/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript"
	src="<%=context_path%>/js/demo/form-validate-demo.min.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/validatorCardId.js"></script>

<script type="text/javascript" src="<%=context_path%>/js/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>

<script type="text/javascript" src="<%=context_path%>/js/plugins/jsTree/jstree.min.js"></script>

<script type="text/javascript" src="<%=context_path%>/js/plugins/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/plugins/image-picker/image-picker.min.js"></script>

<script type="text/javascript" src="<%=context_path%>/js/plugins/fileinput/js/fileinput.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/plugins/fileinput/js/locales/zh.js"></script>
        
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/jquery.form.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/map.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/ajax.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/tree.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/baseFrame/baseFrame.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/plugins/validate/messages_method.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/vide7.1.0/js/video.min.js"></script>
<script type="text/javascript" src="<%=context_path%>/js/vide7.1.0/js/zh-CN.js"></script>
	
<script type="text/javascript">
		$(function(){
			<%
				Object object = request.getAttribute("returnBean");
				if(object != null){
					ReturnBean returnBean = (ReturnBean)object;
			%>
					layer.alert("<%=returnBean.getReturnInfo() %>");
			<%
				}
			%>
		});
		
</script>
