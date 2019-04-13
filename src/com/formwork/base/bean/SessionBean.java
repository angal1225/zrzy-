package com.formwork.base.bean;

public class SessionBean {
	private String login_id;
	private String login_name;
	private String portrait_url;
	private String role_id;
	private String role_name;
	
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPortrait_url() {
		return portrait_url;
	}
	public void setPortrait_url(String portrait_url) {
		this.portrait_url = portrait_url;
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
}
