package com.zrzy.function.bean;

import com.formwork.base.bean.BaseBean;


public class FunctionBean extends BaseBean {
	private String func_id;
	private String func_name;
	private String parent_id;
	private String func_url;
	private String func_type;
	private String is_window;
	private String show_order;
	private String func_status;
	private String note;
	private String funcTreeJson;
	
	private String funcStatusList;
	private String funcTypeList;

	public String getShow_order() {
		return show_order;
	}

	public void setShow_order(String show_order) {
		this.show_order = show_order;
	}

	public String getFuncTypeList() {
		return funcTypeList;
	}

	public void setFuncTypeList(String funcTypeList) {
		this.funcTypeList = funcTypeList;
	}

	public String getFunc_id() {
		return func_id;
	}

	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}

	public String getFunc_name() {
		return func_name;
	}

	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getFunc_url() {
		return func_url;
	}

	public void setFunc_url(String func_url) {
		this.func_url = func_url;
	}

	public String getFunc_type() {
		return func_type;
	}

	public void setFunc_type(String func_type) {
		this.func_type = func_type;
	}

	public String getIs_window() {
		return is_window;
	}

	public void setIs_window(String is_window) {
		this.is_window = is_window;
	}

	public String getFunc_status() {
		return func_status;
	}

	public void setFunc_status(String func_status) {
		this.func_status = func_status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFuncTreeJson() {
		return funcTreeJson;
	}

	public void setFuncTreeJson(String funcTreeJson) {
		this.funcTreeJson = funcTreeJson;
	}

	public String getFuncStatusList() {
		return funcStatusList;
	}

	public void setFuncStatusList(String funcStatusList) {
		this.funcStatusList = funcStatusList;
	}

}
