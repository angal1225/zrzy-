package com.zrzy.function.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zrzy.function.bean.FunctionBean;
import com.zrzy.function.service.FunctionService;
import com.zrzy.people.bean.PeopleBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.formwork.base.bean.ReturnBean;
import com.formwork.base.servlet.BaseServlet;

@WebServlet("/servlet/FunctionServlet/*")
public class FunctionServlet extends BaseServlet {
	private FunctionService functionService = new FunctionService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FunctionBean functionBean = this.getParameterAllMap(request, FunctionBean.class);
		String method = functionBean.getMethod();
		String url = null;
		if ("functionMain".equals(method)) {
			List<Map<String, String>> list = functionService.getFunction(null);
			String funcTreeJson = JSONArray.fromObject(list).toString();
			request.setAttribute("funcTreeJson", funcTreeJson);
			request.setAttribute("statusList", functionService.getBaseDict("02"));
			
			url = "/jsp/function/function_main.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("addMain".equals(method)) {
			request.setAttribute("statusList", functionService.getBaseDict("02"));
			url = "/jsp/function/function_add.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("saveAdd".equals(method)) {
			if (functionService.saveAdd(functionBean)) {
				List<Map<String, String>> list = functionService.getFunction(
						functionBean.getFunc_id(), true);
				JSONArray fromObject = JSONArray.fromObject(list);
				String string = fromObject.toString();
				this.sendJson(response, JSONArray.fromObject(list).toString());
			} else {
				this.sendJson(response, "false");
			}
		} else if ("functionInfo".equals(method)) {
			Map<String, String> map = functionService.getFunctionInfo(functionBean.getFunc_id());
			String json = JSONArray.fromObject(map).toString();
			this.sendJson(response, json);
		} else if ("saveUpdate".equals(method)) {
			if (functionService.saveUpdate(functionBean)) {
				List<Map<String, String>> list = functionService.getFunction(
						functionBean.getFunc_id(), true);
				this.sendJson(response, JSONArray.fromObject(list).toString());
			} else {
				this.sendJson(response, "false");
			}
		} else if ("saveDelete".equals(method)) {
			ReturnBean returnBean = functionService.saveDelete(functionBean.getFunc_id());
			String json = JSONArray.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("checkCode".equals(method)) {
			Map<String, String> map = functionService.getFunctionInfo(functionBean.getFunc_id());
			if(map == null){
				this.sendJson(response, "true");
				return;
			}
			this.sendJson(response, "false");
		}
	}
}
