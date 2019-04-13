package com.zrzy.role.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zrzy.role.bean.RoleBean;
import com.zrzy.role.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.formwork.base.bean.CommonUtils;
import com.formwork.base.bean.ReturnBean;
import com.formwork.base.page.PageBean;
import com.formwork.base.servlet.BaseServlet;

@WebServlet("/servlet/RoleServlet/*")
public class RoleServlet extends BaseServlet {

	private RoleService roleService = new RoleService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RoleBean roleBean = this.getParameterAllMap(request, RoleBean.class);
		String method = roleBean.getMethod();
		String url = null;

		if ("roleMain".equals(method)) {
			request.setAttribute("statusList", roleService.getBaseDict("02"));
			url = "/jsp/role/role_main.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("roleQueryList".equals(method)) {
			PageBean pageBean = roleService.roleQueryList(roleBean);
			String json = JSONObject.fromObject(pageBean).toString();
			this.sendJson(response, json);
		} else if ("addMain".equals(method)) {
			request.setAttribute("statusList", roleService.getBaseDict("02"));
			url = "/jsp/role/role_add.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("upMain".equals(method)) {
			Map<String, String> roleMap = roleService.roleQueryInfo(roleBean.getRole_id());
			request.setAttribute("roleMap", roleMap);
			request.setAttribute("statusList", roleService.getBaseDict("02"));
			url = "/jsp/role/role_update.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("addSave".equals(method)) {
			ReturnBean returnBean = new ReturnBean();
			try {
				roleService.addSave(roleBean);
			} catch (RuntimeException e) {
				e.printStackTrace();
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo(e.getMessage());
			}
			String json = JSONObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("upSave".equals(method)) {
			ReturnBean returnBean = new ReturnBean();
			try {
				roleService.upSave(roleBean);
			} catch (RuntimeException e) {
				e.printStackTrace();
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo(e.getMessage());
			}
			String json = JSONObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("deleteSave".equals(method)) {
			ReturnBean returnBean = null;
			try {
				returnBean = roleService.saveDelete(roleBean.getCk_sign());
			} catch (Exception e) {
				e.printStackTrace();
				returnBean = new ReturnBean();
				returnBean.setReturnValue("false");
				returnBean.setReturnInfo(e.getMessage());
			}
			url = "roleMain";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("funcTree".equals(method)) {
			List<Map<String, String>> list = roleService.getFuncTree(roleBean.getRole_id());
			String json = JSONArray.fromObject(list).toString();
			request.setAttribute("funcTree", json);
			request.setAttribute("role_id", roleBean.getRole_id());
			url = "/jsp/role/func_tree.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("saveRoleFunc".equals(method)) {
			ReturnBean returnBean = new ReturnBean();
			try {
				roleService.saveRoleFunc(roleBean.getRole_id(), roleBean.getCk_sign());
			} catch (RuntimeException e) {
				e.printStackTrace();
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo(e.getMessage());
			}
			String json = JSONObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("checkRoleId".equals(method)) {
			Map<String, String> map = roleService.roleQueryInfo(roleBean.getRole_id());
			if(map == null){
				this.sendJson(response, "true");
				return;
			}
			this.sendJson(response, "false");
		}
	}
}
