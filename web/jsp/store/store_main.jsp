<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/2 0002
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ include file="/jsp/common/include.jsp"%>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <script type="text/javascript">
        <%
        Object object2 = request.getAttribute("sign");
        System.out.println(object2);
        if(object2 != null){
            boolean sign = (Boolean)object2;
            if(sign){%>
        layer.alert("操作成功！");
        <%}else{%>
        layer.alert("操作失败！");
        <%}
    }
    %>

        $(function () {
            initTable();
        });

        function initTable(){

            var temp = {
                showToggle:true,
                method:'POST',
                dataType:'json',
                contentType: "application/x-www-form-urlencoded",
                cache: false,
                striped: true,
                sortable: true,
                sortOrder: "asc",
                sidePagination: "server",
                url:"queryList",
                showColumns:true,
                pagination:true,
                paginationLoop:true,
                minimumCountColumns:2,
                pageNumber:1,
                pageSize: 10,
                pageList: [10, 20, 50, 100],
                uniqueId: 'warehouseId',
                showExport: true,
                exportDataType: 'all',
                toolbar: '#toolbar',
                queryParams : queryParams,
                search: false,
                strictSearch: false,
                showRefresh: false,
                clickToSelect: true,
                escape: true,
                singleSelect: false,
                columns: [
                    {checkbox: true},
                    {field : 'warehouseId', title : '仓库名称', align : 'center', valign : 'middle', sortable : true},
                    {field : 'address', title : '仓库地址', align : 'center', valign : 'middle', sortable : true},
                    {field : 'warehouseKeeper', title : '仓库管理员', align : 'center', valign : 'middle'},
                    {field : 'warehouseName', title : '仓库名', align : 'center', valign : 'middle'},
                    {field : 'op', title : '操作', align : 'center', valign : 'middle',
                        formatter : function (value, row, index){
                            var temp = "<a href='#' onclick='upSave(\""+row.warehouseId+"\")'>编辑</a>";
                            temp += "&nbsp;&nbsp;<a href='#' onclick='deleteSave(\""+row.warehouseId+"\")'>删除</a>";
                            return temp;

                        }}
                ]
            };


            $("#demo-table").bootstrapTable(temp);
        }

        function addSave(){
            layer.open({
                type: 2,
                title: '新增页面',
                shadeClose: true,
                area: ['80%', '100%'],
                skin: 'layui-layer-rim',
                content: 'add'
            });
        }

        function upSave(people_id){
            layer.open({
                type: 2,
                title: '新增页面',
                shadeClose: true,
                area: ['80%', '100%'],
                skin: 'layui-layer-rim',
                content: 'upMain?people_id=' + people_id
            });
        }

        function deleteSave(people_id){
            var url = 'deleteSave?people_id=' + people_id;
            $("#toolbar").attr("action", url);
            $("#toolbar").submit();
        }

        function deleteSaveBat(){
            //var people_ids = selectBootstrapTable($('#demo-table'), "people_id");
            var arrayList = $('#demo-table').bootstrapTable('getAllSelections');
            var people_ids = "";
            $.each(arrayList, function (){
                people_ids += this.people_id;
                people_ids += ",";
            });

            if(people_ids != ""){
                people_ids = people_ids.substring(0, people_ids.length-1);
            }else{
                layer.alert("请选择删除项！");
                return;
            }

            layer.confirm("是否要删除当前数据！", function(){
                var url = 'deleteSaveBat?people_ids=' + people_ids;
                $("#toolbar").attr("action", url);
                $("#toolbar").submit();
            },function(){
                layer.alert("你真的不删了！");
            });
        }

        function queryData(){
            $('#demo-table').bootstrapTable('refresh');
        }

    </script>
</head>


<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <div class="example-wrap">
                        <h4 class="example-title">仓库管理</h4>
                        <div>
                            <form class="form-inline" style="float: left; width: 100%" method="post" id="toolbar">
                                <input type="hidden" name="ck_sign" id="ck_sign"/>
                                <div class="form-group">
                                    <label>仓库编号:</label>
                                    <input type="text" class="form-control" name="warehouseId" id="warehouseId">
                                </div>
                                <div class="form-group">
                                    <label>仓库名称:</label>
                                    <input type="text" class="form-control" name="warehouseName" id="warehouseName" />
                                </div>

                                <br/>
                                <div class="form-group">
                                    <button type="button" id="queryBtn" onclick="queryData();" class="btn btn-primary">查询</button>
                                </div>
                                <div class="form-group btn-right">
                                    <button type="button" class="btn btn-primary" id="addBtn" onclick="addSave();">新增</button>
                                </div>
                                <div class="form-group btn-right">
                                    <button type="button" class="btn btn-primary" id="addBtn" onclick="deleteSaveBat();">删除</button>
                                </div>
                            </form>
                        </div>
                        <div class="example">
                            <table id="demo-table" class="table table-bordered table-hover table-striped" style="word-break:break-all; word-wrap:break-all;">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

