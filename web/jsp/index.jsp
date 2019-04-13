<!DOCTYPE html>
<%
	response.setDateHeader ("Expires", 0);
	String context_path = request.getContextPath();
	request.setCharacterEncoding("UTF-8");
%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=context_path%>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=context_path%>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=context_path%>/css/animate.min.css" rel="stylesheet">
    <link href="<%=context_path%>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    
    <script type="text/javascript" src="<%=context_path%>/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript" >
 	
		$(function(){
			addFunctionNode(${loginBean.jsonFunc});
		});
		
		function addFunctionNode(node) {
			$.each(node,function(i,n){
				if(n.pId == '0000'){
					var functionNode = $("<li></li>");
					if(n.link != ''){
						n.link = "../" + n.link;
					}
					var content = $("<a class=\"J_menuItem\" href=\""+n.link+"\"><i class=\"fa fa-"+n.icon+"\"></i><span class=\"nav-label\">"+n.name+"</span></a>");
					functionNode.append(content);
					if(addSecondChildNode(n.id, functionNode, node)){
						content.append("<span class=\"fa arrow\"></span>");
					}
					$('#side-menu').append(functionNode);
				}
			});
		}
		
		function addSecondChildNode(parentId, parentNode, node){
			var sign = false;
			var childs_node = $("<ul class=\"nav nav-second-level\"></ul>");
			$.each(node,function(i,n){
				if(parentId == n.pId){
					sign = true;
					var child_node = $("<li></li>");
					if(n.link != ''){
						n.link = "../" + n.link;
					}
					var content = $("<a class=\"J_menuItem\" href=\""+n.link+"\">"+n.name+"</a>");
					child_node.append(content);
					childs_node.append(child_node);
					if(addThirdChildNode(n.id, child_node, node)){
						content.append("<span class=\"fa arrow\"></span>");
					}
					
				}
			})
			if(sign){
				parentNode.append(childs_node);
			}
			return sign;
		}
		
		function addThirdChildNode(parentId, parentNode, node){
			var sign = false;
			var childs_node = $("<ul class=\"nav nav-third-level\"></ul>");
			$.each(node,function(i,n){
				if(parentId == n.pId){
					sign = true;
					var child_node = $("<li></li>");
					if(n.link != ''){
						n.link = "../" + n.link;
					}
					var content = $("<a class=\"J_menuItem\" href=\""+n.link+"\">"+n.name+"</a>");
					child_node.append(content);
					childs_node.append(child_node);
				}
			})
			if(sign){
				parentNode.append(childs_node);
			}
			
			return sign;
		}
		
		function logout(){
			window.location.href='logout'; 
		}

	</script>
	<script type="text/javascript" src="<%=context_path%>/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="<%=context_path%>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script type="text/javascript" src="<%=context_path%>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="<%=context_path%>/js/plugins/layer/layer.min.js"></script>
    <script type="text/javascript" src="<%=context_path%>/js/hplus.min.js?v=4.1.0"></script>
    <script type="text/javascript" src="<%=context_path%>/js/contabs.min.js"></script>
    <script type="text/javascript" src="<%=context_path%>/js/plugins/pace/pace.min.js"></script>
	
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="${SERVICE_SESSIONBEAN.portrait_url}" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
	                                <span class="block m-t-xs"><strong class="font-bold">${SERVICE_SESSIONBEAN.login_name}</strong></span>
	                                <span class="text-muted text-xs block">${SERVICE_SESSIONBEAN.role_name}<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="changePasswd">修改密码</a>
                                </li>
                                <li><a class="J_menuItem" href="upSelfMain?login_id=${SERVICE_SESSIONBEAN.login_id}">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="#" onclick="logout()">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">诗远
                        </div>
                    </li>

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="#" class="roll-nav roll-right J_tabExit" onclick="logout()"><i class="fa fa fa-sign-out"></i> 切换用户</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="index_v148b2.html?v=4.0" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">和敬清寂
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
</body>


</html>
