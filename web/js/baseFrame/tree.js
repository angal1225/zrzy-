function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

function tree_async(tree_id,url){
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			fontCss: getFontCss
		},
		async: {
			enable: true,
			url:url,
			autoParam:["id", "name", "level"],
			dataFilter: filter
		},
		callback: {
			onClick: onClick
		}
	};
	
	$.fn.zTree.init($('#'+tree_id), setting);
}

function tree(tree_id,zNodes){
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			fontCss: getFontCss
		},
		callback: {
			onClick: onClick
		}
	};
	
	$.fn.zTree.init($('#'+tree_id), setting, zNodes);
}

function tree_check_async(tree_id,url){
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			fontCss: getFontCss
		},
		async: {
			enable: true,
			url:url,
			autoParam:["id", "name", "level"],
			dataFilter: filter
		},
		callback: {
			onClick: onClick
		}
	};
	
	$.fn.zTree.init($('#'+tree_id), setting);
}

function tree_check(tree_id,zNodes){
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		view: {
			fontCss: getFontCss
		},
		callback: {
			onClick: onClick
		}
	};
	
	$.fn.zTree.init($('#'+tree_id), setting, zNodes);
}

function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}

function onClick(event,treeId,treeNode){
}

function jstree(jstreeObject, data){
	$(jstreeObject).jstree({
		"core":{
			"check_callback":true,
			"data":data
		},
		"plugins":["types", "wholerow"],
		"types":{
			"default":{"icon":"fa fa-folder"},
			"html":{"icon":"fa fa-file-code-o"},
			"svg":{"icon":"fa fa-file-picture-o"},
			"css":{"icon":"fa fa-file-code-o"},
			"img":{"icon":"fa fa-file-image-o"},
			"js":{"icon":"fa fa-file-text-o"},
			"flash" : {"icon" : "glyphicon glyphicon-flash"},
			"ok" : {"icon" : "glyphicon glyphicon-ok"}
		}
	}).on('select_node.jstree', function (event, data) {
		jstreeOnClick(event,data);
	});
}

function jstreeCheckbox(jstreeObject, data){
	$(jstreeObject).jstree({
		"core":{
			"check_callback":true,
			"data":data
		},
		"plugins":["checkbox", "types", "wholerow"],
		"types":{
			"default":{"icon":"fa fa-folder"},
			"html":{"icon":"fa fa-file-code-o"},
			"svg":{"icon":"fa fa-file-picture-o"},
			"css":{"icon":"fa fa-file-code-o"},
			"img":{"icon":"fa fa-file-image-o"},
			"js":{"icon":"fa fa-file-text-o"},
			"flash" : {"icon" : "glyphicon glyphicon-flash"},
			"ok" : {"icon" : "glyphicon glyphicon-ok"}
		}
	}).on('select_node.jstree', function (event, data) {
		jstreeOnClick(event,data);
	});
}


function jstreeOnClick(event,data){
}


