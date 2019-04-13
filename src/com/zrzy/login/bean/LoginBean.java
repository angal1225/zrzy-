package com.zrzy.login.bean;

import com.formwork.base.bean.BaseBean;

public class LoginBean extends BaseBean {
	private String login_id;
	private String login_name;
	private String login_pd;
	private String login_role;
	private String login_status;
	private String status_name;
	private String create_login;
	private String create_date;
	private String pageNo;
	private String jsonFunc;
	private String portrait_url;

	private String new_passwd;
	private String old_passwd;

	public String getPortrait_url() {
		return portrait_url;
	}
	public void setPortrait_url(String portrait_url) {
		this.portrait_url = portrait_url;
	}
	public String getNew_passwd() {
		return new_passwd;
	}
	public void setNew_passwd(String new_passwd) {
		this.new_passwd = new_passwd;
	}
	public String getOld_passwd() {
		return old_passwd;
	}
	public void setOld_passwd(String old_passwd) {
		this.old_passwd = old_passwd;
	}
	public String getJsonFunc() {
		return jsonFunc;
	}
	public void setJsonFunc(String jsonFunc) {
		this.jsonFunc = jsonFunc;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
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
	public String getLogin_pd() {
		return login_pd;
	}
	public void setLogin_pd(String login_pd) {
		this.login_pd = login_pd;
	}
	public String getLogin_role() {
		return login_role;
	}
	public void setLogin_role(String login_role) {
		this.login_role = login_role;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public String getCreate_login() {
		return create_login;
	}
	public void setCreate_login(String create_login) {
		this.create_login = create_login;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
}
