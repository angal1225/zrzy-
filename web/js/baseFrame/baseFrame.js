
// 获取form表单信息
jQuery.prototype.serializeObject=function(){
	var obj=new Object();
	$.each(this.serializeArray(),function(index,param){
		if(!(param.name in obj)){
			obj[param.name]=param.value;
		}
	});
	return obj;
};

//bootstrapTable获取参数
function queryParams(params) {
	var param = $("#toolbar").serializeObject();
	param['limit'] = this.limit;
	param['offset'] = this.offset;
	param['page'] = this.pageNumber;
	param['rows'] = this.pageSize;
	param['sort'] = this.sortName;
	param['order'] = this.sortOrder;
    return param;
}

//bootstrapTable刷新
function doQuery(tableId){
    $(tableId).bootstrapTable('refresh');
}

function initBootstrapTable(parameters){
    $(parameters.tableObj).bootstrapTable({
    	showToggle:true,
        method:'POST',
        dataType:'json',
        contentType: "application/x-www-form-urlencoded",
        cache: false,
        striped: true,
        sortable: true,
        sortOrder: "asc",
        sidePagination: "server",
        url:parameters.url,
        showColumns:true,
        pagination:true,
        paginationLoop:true,
        queryParams : queryParams,
        minimumCountColumns:2,
        pageNumber:1,
        pageSize: 10,
        pageList: [10, 20, 50, 100],
        uniqueId: parameters.uniqueId,
        showExport: true,                    
        exportDataType: 'all',
        toolbar: '#toolbar',
        search: false,
        strictSearch: false,
        showRefresh: false,
        clickToSelect: true,
        escape: true,
        singleSelect: parameters.singleSelect,
        columns: parameters.columns
    });
}

function  operateOpinionFormatter(value, row, index){
    if(value.length>5){  
        return "<span title='"+value+"'>"+value.substring(0,5)+"..."+"</span>";  
    }else{  
        return "<span title='"+value+"'>"+value.substring(0,value.length)+"</span>";  
    }  
}

function selectBootstrapTable(tableObject, name){
	var arrayList = $(tableObject).bootstrapTable('getAllSelections');
	if(arrayList.length > 0){
		var val = '';
		$.each(arrayList,function(index,param){
			val += param[name] + ',';
		});
		
		if(val != ''){
			return val.substring(0,val.lastIndexOf(','));
		}
	}
}

function getParameter(parameter){
	parameter = parameter.replace(/\W/g,'');
	var parameterRegExp = new RegExp("(^|&)"+parameter+"=([^&]+)(&|$)");
	var m;
	if (m = window.location.search.substr(1).match(parameterRegExp)){
		return m[2];
	}
	return null;
}

function initSelect(selectObject, data, defaultValue){
    for(var i in data){
		if(data[i].value==defaultValue){
			selectObject.append("<option value='"+data[i].value+"' selected=true>"+data[i].text+"</option>");
		}else{
			selectObject.append("<option value='"+data[i].value+"'>"+data[i].text+"</option>");
		}
    }
}

function initSelectLinkage(selectObject, triggerSelectObject, url, defaultValue){
	triggerSelectObject.on('change', function(){
		var map = new Map();
		map.put($(this).attr('name'), $(this).children('option:selected').val());
		select_change(map,url,selectObject.attr('id'),defaultValue);
	});
	
	var value = triggerSelectObject.children('option:selected').val()
	if(value != null && value != ""){
		var map = new Map();
		map.put(triggerSelectObject.attr('name'), value);
		select_change(map,url,selectObject.attr('id'),defaultValue);
	}
}

//datetimepicker日期，format年月日
function date(dateObject){
	$(dateObject).datetimepicker({
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
}

//datetimepicker日期，format年月
function datemonth(dateObject){
	$(dateObject).datetimepicker({
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
}


//datetimepicker年
function year(dateObject){
	dateObject.datetimepicker({
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
}

//datetimepicker日期时间
function datetime(dateObject){
	$(dateObject).datetimepicker({
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
}

//datetimepicker时间
function time(dateObject){
	$(dateObject).datetimepicker({
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
}

function databetween(dateObject){
	$(dateObject).datepicker({
		format: 'yyyy-mm-dd',
		weekStart: 0,
		autoclose:false,
		language: 'zh-CN',
		clearBtn: false,
		startView:'day',
		todayBtn:'linked',
		todayHighlight:true,
		keyboardNavigation:true,
		forceParse:true
	});
}

function commitFormBack(data){
	if(data.returnValue=="true"){
		layer.alert(data.returnInfo);
	}else if(data.returnValue=="false"){
		layer.alert(data.returnInfo);
	}else{
		layer.alert(data.returnInfo);
	}
}

function commitFormBackIform(data){
	if(data.returnValue=="true"){
		layer.open({
			content: data.returnInfo,
			end: function(index, layero){
				parent.location.reload();
			}
		});
	}else if(data.returnValue=="false"){
		layer.open({
			content: data.returnInfo,
			end: function(index, layero){
				parent.location.reload();
			}
		});
	}else{
		layer.open({
			content: data,
			end: function(index, layero){
				parent.location.reload();
			}
		});
	}
}

function closeIform(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
