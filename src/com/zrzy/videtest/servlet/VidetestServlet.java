package com.zrzy.videtest.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formwork.base.bean.ReturnBean;
import com.formwork.base.page.PageBean;
import com.formwork.base.servlet.BaseServlet;
import com.zrzy.common.FileUtils;
import com.zrzy.people.bean.PeopleBean;
import com.zrzy.people.service.PeopleService;

import net.sf.json.JSONObject;

@WebServlet("/servlet/VidetestServlet/*")
public class VidetestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private PeopleService peopleService = new PeopleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PeopleBean peopleBean = this.getParameterAllMap(request, PeopleBean.class);
		String method = peopleBean.getMethod();
		
		if("main".equals(method)){
			request.getRequestDispatcher("/jsp/videtest/videtest.jsp").forward(request, response);
		}else if("queryList".equals(method)){
			
//			模拟从数据库中获取数据，拼接pageBean对象开始
			PageBean pageBean = new PageBean();
			
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", "csspbm");
			map.put("name", "测试视频名称");
			map.put("path", "http://localhost:8088/images/20181113_135207.mp4");
			list.add(map);
			
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("code", "csspbm2");
			map2.put("name", "测试视频名称2");
			map2.put("path", "http://localhost:8088/images/20181109_100038.mp4");
			list.add(map2);
			
			pageBean.setRows(list);
			pageBean.setTotal("2");
//			模拟从数据库中获取数据，拼接pageBean对象结束
			
			
			String resultString = JSONObject.fromObject(pageBean).toString();
			this.sendJson(response, resultString);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
