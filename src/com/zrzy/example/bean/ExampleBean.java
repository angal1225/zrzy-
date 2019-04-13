package com.zrzy.example.bean;

import com.formwork.base.bean.BaseBean;

public class ExampleBean extends BaseBean {
	private String test_type;
	private String test_dict;
	public String getTest_type() {
		return test_type;
	}
	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}
	public String getTest_dict() {
		return test_dict;
	}
	public void setTest_dict(String test_dict) {
		this.test_dict = test_dict;
	}
}
