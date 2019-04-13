package com.zrzy.example.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.formwork.base.servlet.BaseServlet;
import com.zrzy.example.bean.ExampleBean;
import com.zrzy.example.service.ExampleService;
import com.zrzy.function.service.FunctionService;

import net.sf.json.JSONArray;

@WebServlet("/servlet/ExampleServlet/*")
public class ExampleServlet extends BaseServlet {
	private ExampleService exampleService = new ExampleService();
	private FunctionService functionService = new FunctionService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ExampleBean exampleBean = this.getParameterAllMap(request, ExampleBean.class);
		String method = exampleBean.getMethod();
		String url = null;
		if ("exampleMain".equals(method)) {
			request.setAttribute("testTypeList", exampleService.getBaseDict("-1"));
			url = "/jsp/example/example_linkage.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("linkage".equals(method)) {
			List<Map<String, String>> list = exampleService.getBaseDict(exampleBean.getTest_type());
			String testTypeList = JSONArray.fromObject(list).toString();
			this.sendJson(response, testTypeList);
		} else if ("exampleDate".equals(method)) {
			url = "/jsp/example/example_date.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("exampleTree".equals(method)) {
			List<Map<String, String>> list = functionService.getFunction(null);
			String funcTreeJson = JSONArray.fromObject(list).toString();
			request.setAttribute("funcTreeJson", funcTreeJson);
			
			url = "/jsp/example/example_tree.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}
