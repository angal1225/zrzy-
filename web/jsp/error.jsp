<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<%=context_path%>/css/login.css" rel="stylesheet" type="text/css"/>
<title></title>
<script>

</script>
</head>
<body>
 <h2>
         出现异常啦
 </h2>
 <hr/>
   <h3 style="color:red">
    ${exception.message}
    </h3>
    <br/>
    <div style="display:none;">
    </div>
</body>
</html>
