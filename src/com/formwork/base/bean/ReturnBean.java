package com.formwork.base.bean;

public class ReturnBean {
	private String returnInfo = "操作成功";
	private String returnValue = CommonUtils.SERVICE_RETURN_SUCCESS;

	public ReturnBean(){
	}
	
	public ReturnBean(String returnInfo, String returnValue){
		this.returnInfo = returnInfo;
		this.returnValue = returnValue;
	}

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
}
