package com.formwork.base.bean;

import java.io.File;

public class CommonUtils {
	public static final String BASE_PATH = "file" + File.separatorChar;
	public static final String FILE_PATH = BASE_PATH + "upload" + File.separatorChar;
	public static final String TEMP_PATH = BASE_PATH + "temp" + File.separatorChar;

	public static final String SERVICE_RETURN_SUCCESS = "true";
	public static final String SERVICE_RETURN_FAIL = "false";
	public static final String SERVICE_RETURNBEAN = "RETURNBEAN";
	public static final String SERVICE_SESSIONBEAN = "SERVICE_SESSIONBEAN";
	

}
