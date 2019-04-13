package com.formwork.base.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.formwork.base.bean.CommonUtils;
import com.formwork.base.bean.SessionBean;
import com.formwork.utils.ClassUtil;
import com.zrzy.common.DateFormatUtils;

public abstract class BaseServlet extends HttpServlet {
	public Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> map2 = new HashMap<String, String>();
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String[] values = entry.getValue();
				String value = "";
				for (String temp : values) {
					value += temp + ",";
				}
				value = value.substring(0, value.length() - 1);
				map2.put(entry.getKey(), value);
			}
		}

		return map2;
	}

	public <T> T getParameterMap(HttpServletRequest request,
			HttpServletResponse response, Class<?> cls) {
		Map<String, String> map = getParameterMap(request);
		T object = (T)ClassUtil.setFieldValue(cls, map);

		return object;
	}

	public Map<String, String> getParameterFileMap(HttpServletRequest request) throws FileUploadException,
			FileNotFoundException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		String tempPath = basePath + CommonUtils.TEMP_PATH;
		String filePath = basePath + CommonUtils.FILE_PATH;

		File file = new File(tempPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		File temp = new File(filePath);
		if (!temp.exists()) {
			temp.mkdirs();
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(
				1024 * 1024 * 50, file);
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				diskFileItemFactory);
		
//		获取页面所有参数，包括文件和非文件参数
		List<FileItem> list = servletFileUpload.parseRequest(request);
		for (FileItem fileItem : list) {
			
			
			if (fileItem.isFormField()) {
				map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
			} else {
				String name = getFileName(fileItem.getName());
				String path = filePath + name;
				try {
					fileItem.write(new File(path));
//					InputStream inputStream = fileItem.getInputStream();
//					
//					FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
//					byte[] bs = new byte[1024];
//					while(inputStream.read(bs) != -1){
//						fileOutputStream.write(bs);
//						bs = new byte[1024];
//					}
//					
				} catch (Exception e) {
					e.printStackTrace();
				}
				map.put(fileItem.getFieldName(), path);
			}
			
			
		}
		return map;
	}
	
	public <T> T getParameterFileMap(HttpServletRequest request,
			HttpServletResponse response, Class<?> cls) throws FileUploadException,
			FileNotFoundException, IOException {
		Map<String, String> map = getParameterFileMap(request);
		T object = (T)ClassUtil.setFieldValue(cls, map);

		return object;
	}

	public Map<String, String> getAttributeMap(HttpServletRequest request) {
		Map<String, String> map = getParameterMap(request);
		Enumeration<String> names = request.getAttributeNames();

		while (names.hasMoreElements()) {
			String name = names.nextElement();
			Object value = request.getAttribute(name);
			if (value instanceof String) {
				map.put(name, (String) request.getAttribute(name));
			}
		}

		return map;
	}

	public <T> T getAttributeMap(HttpServletRequest request,
			HttpServletResponse response, Class<?> cls) {
		Map<String, String> map = getAttributeMap(request);
		T object = (T)ClassUtil.setFieldValue(cls, map);

		return object;
	}

	public Map<String, String> getParameterAllMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> parameterMap = getParameterMap(request);
		Map<String, String> attributeMap = getAttributeMap(request);
		Map<String, String> fileMap = null;
		try {
			fileMap = getParameterFileMap(request);
		} catch (Exception e) {
		}

		if (parameterMap != null && parameterMap.size() > 0) {
			map.putAll(parameterMap);
		}

		if (attributeMap != null && attributeMap.size() > 0) {
			map.putAll(attributeMap);
		}

		if (fileMap != null && fileMap.size() > 0) {
			map.putAll(fileMap);
		}

		return map;
	}

	public <T> T getParameterAllMap(HttpServletRequest request, Class<?> cls) {
		Map<String, String> map = getParameterAllMap(request);
		T object = (T)ClassUtil.setFieldValue(cls, map);

		return object;
	}

	public String[] getValues(String value) {
		String[] values = null;
		if(value != null){
			values = value.split(",");
		}
		return values;
	}
	
	public SessionBean getSessionBean(HttpServletRequest request){
		Object object = request.getSession().getAttribute(CommonUtils.SERVICE_SESSIONBEAN);
		if(object != null){
			return (SessionBean)object;
		}
		return null;
	}
	
	public static void send(HttpServletResponse response, String contentType, String content) {

		contentType = contentType + ";charset=UTF-8";
		response.setContentType(contentType);
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");

		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static void sendJson(HttpServletResponse response, String jsonString) {
		send(response, "application/json", jsonString);
	}

	public static void sendText(HttpServletResponse response, String jsonString) {
		send(response, "text/plain", jsonString);
	}

	public String getFileName(String name) {
		String time = DateFormatUtils.format(DateFormatUtils.FORMAT2_FULL);
		String postfix = name.substring(name.lastIndexOf("."));
		name = name.substring(0, name.lastIndexOf("."));

		name = name + time + postfix;
		return name;
	}

}
