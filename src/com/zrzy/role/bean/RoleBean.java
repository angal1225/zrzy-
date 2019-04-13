package com.zrzy.role.bean;

import com.formwork.base.bean.BaseBean;


public class RoleBean extends BaseBean {
	private String role_id;
	private String role_name;
	private String role_status;
	private String status_name;
	private String ck_sign;
	private String func_id;
	private String status;
	
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_status() {
		return role_status;
	}
	public void setRole_status(String role_status) {
		this.role_status = role_status;
	}
	public String getCk_sign() {
		return ck_sign;
	}
	public void setCk_sign(String ck_sign) {
		this.ck_sign = ck_sign;
	}
	public String getFunc_id() {
		return func_id;
	}
	public void setFunc_id(String func_id) {
		this.func_id = func_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
