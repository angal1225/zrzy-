
function combobox_change(map,url,changeId){
	select_change(map,url,changeId,'','combobox_callback');
}

function select_change(map,url,select_id,select_value,change_callback){
	var parameter = map.toString();
	$.ajax({
		type: "POST",
		async:true,
		url: url,
		data: parameter,
		dataType: 'json',
		success: function(data){
			if(change_callback==null){
				select_callback(data,select_id,select_value);
			}else{
				change_callback = change_callback+"("+"data,select_id,select_value"+")";
				eval(change_callback);
			}
		}
	});
}

function select_callback(data,select_id,select_value){
	var select_obj = $('#'+select_id);
	select_obj.empty();
	for(var i=0;i<data.length;i++){
		if(data[i].value==select_value){
			select_obj.append("<option value='"+data[i].value+"' selected=true>"+data[i].text+"</option>");
		}else{
			select_obj.append("<option value='"+data[i].value+"'>"+data[i].text+"</option>");
		}
	}
}

function combobox_callback(data,select_id,select_value){
	$('#'+select_id).combobox('clear');
	$('#'+select_id).combobox('loadData',data);
}

function ajax_json(map, url, ajaxback_function){
	var parameter = map.toString();
	$.ajax({
		type: "POST",
		async:true,
		url: url,
		data: parameter,
		dataType: 'json',
		success: function(data){
			if(ajaxback_function==null){
				ajaxback(data);
			}else{
				ajaxback_function = ajaxback_function+"("+"data"+")";
				eval(ajaxback_function);
			}
		}
	});
}

function ajax_html(map, url, ajaxback_function){
	var parameter = map.toString();
	$.ajax({
		type: "POST",
		async:true,
		url: url,
		data: parameter,
		dataType: 'html',
		success: function(data){
			if(ajaxback_function==null){
				ajaxback(data);
			}else{
				ajaxback_function = ajaxback_function+"("+"data"+")";
				eval(ajaxback_function);
			}
		}
	});
}

function ajaxback(data){
	data = eval("["+data+"]")[0];
	if(data.returnValue=="true"){
		$.messager.alert('提交结果',data.returnInfo);
	}else if(data.returnValue=="false"){
		$.messager.alert('提交结果',data.returnInfo);
	}else{
		$.messager.alert('提交结果',data);
	}
}
