<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/27 0027
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>注册</title>

    <!-- GOOGLE FONTS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

</head>
<body style="background-color: #E2E2E2;">
<div class="container">
    <div class="row text-center " style="padding-top:100px;">
        <div class="col-md-12">
            <img src="<%=request.getContextPath()%>/jsp/img/2.jpg" />
        </div>
    </div>
    <div class="row ">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

            <div class="panel-body">
                <form role="form">
                    <hr />
                    <h5>请注册账号</h5>
                    <br />
                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                        <input type="text" class="form-control" placeholder="Your Username " />
                    </div>
                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                        <input type="password" class="form-control"  placeholder="Your Password" />
                    </div>
                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input type="checkbox" /> Remember me
                        </label>
                        <span class="pull-right">
                                                   <a href="<%=request.getContextPath()%>/jsp/login.jsp" >Forget password ? </a>
                                            </span>
                    </div>

                    <a href="<%=request.getContextPath()%>/jsp/login.jsp" class="btn btn-primary ">Login Now</a>
                    <hr />
                    Not register ? <a href="<%=request.getContextPath()%>/jsp/login.jsp" >click here </a> or go to <a href="<%=request.getContextPath()%>/jsp/login.jsp">Home</a>
                </form>
            </div>

        </div>


    </div>
</div>

</body>
</html>
