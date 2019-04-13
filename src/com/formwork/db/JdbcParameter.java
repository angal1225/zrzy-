package com.formwork.db;

public class JdbcParameter {
	private String parameterName;
	private int parameterType;
	private String parameterValue;
	
	public JdbcParameter(String parameterName, int parameterType, String parameterValue){
		this.parameterName = parameterName;
		this.parameterType = parameterType;
		this.parameterValue = parameterValue;
	}

	public JdbcParameter(String goodTypeId, int varchar, int goodTypeId1) {
	}

	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public int getParameterType() {
		return parameterType;
	}
	public void setParameterType(int parameterType) {
		this.parameterType = parameterType;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
}
