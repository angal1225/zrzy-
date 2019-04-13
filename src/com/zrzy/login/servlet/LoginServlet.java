package com.zrzy.login.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zrzy.common.FileUtils;
import com.zrzy.common.MD5Utils;
import com.zrzy.login.bean.LoginBean;
import com.zrzy.login.service.LoginService;
import com.formwork.base.bean.CommonUtils;
import com.formwork.base.bean.KeyValueBean;
import com.formwork.base.bean.ReturnBean;
import com.formwork.base.bean.SessionBean;
import com.formwork.base.page.PageBean;
import com.formwork.base.servlet.BaseServlet;

@WebServlet("/servlet/LoginServlet/*")
public class LoginServlet extends BaseServlet {
	private LoginService loginService = new LoginService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginBean loginBean = this.getParameterAllMap(request, LoginBean.class);
		String method = loginBean.getMethod();
		String url = "/jsp/login.jsp";
		if ("main".equals(method)) {
			url = "/jsp/login/login_main.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("queryList".equals(method)) {
			PageBean pageBean = loginService.getPageList(loginBean);
			String pageBeanJson = JSONObject.fromObject(pageBean).toString();
			System.out.println(pageBeanJson);
			this.sendJson(response, pageBeanJson);
		} else if ("addMain".equals(method)) {
			request.setAttribute("roleDictList", loginService.getRoleDict());
			request.setAttribute("statusList", loginService.getBaseDict("02"));
			url = "/jsp/login/login_add.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("addSave".equals(method)) {
			ReturnBean returnBean = new ReturnBean();
			loginBean.setLogin_pd(MD5Utils.encode("123456"));
			if (!loginService.addLogin(loginBean)) {
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("操作失败");
			}
			JSONObject jsonObject = new JSONObject();
			String json = jsonObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("upMain".equals(method)) {
			request.setAttribute("roleDictList", loginService.getRoleDict());
			request.setAttribute("statusList", loginService.getBaseDict("02"));
			Map<String, String> loginMap = loginService.getLoginInfo(loginBean);
			request.setAttribute("loginMap", loginMap);
			url = "/jsp/login/login_update.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("upSave".equals(method)) {
			ReturnBean returnBean = new ReturnBean();
			if (!loginService.updateLogin(loginBean)) {
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("操作失败");
			}
			JSONObject jsonObject = new JSONObject();
			String json = jsonObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("deleteSave".equals(method)) {
			String[] checks = this.getValues(loginBean.getCk_sign());
			ReturnBean returnBean = new ReturnBean();
			if (!loginService.deleteLogin(checks)) {
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("操作失败");
			}
			request.setAttribute("returnBean", returnBean);
			url = "/jsp/login/login_main.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("loginCheck".equals(method)) {
			try {
				SessionBean sessionBean = getSessionBean(request);
				ReturnBean returnBean = new ReturnBean();

				if (sessionBean != null) {
					loginBean.setLogin_id(sessionBean.getLogin_id());
					returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_SUCCESS);
				} else {
					returnBean = loginService.loginCheck(loginBean);
				}

				if (CommonUtils.SERVICE_RETURN_SUCCESS.equals(returnBean.getReturnValue())) {
					sessionBean = loginService.getSessionBean(loginBean
							.getLogin_id());
					if (sessionBean == null) {
						url = "/jsp/login.jsp";
					}else{
						request.getSession().setAttribute(CommonUtils.SERVICE_SESSIONBEAN, sessionBean);
						request.getSession().getServletContext().setAttribute(
								loginBean.getLogin_id(),
								request.getSession().getId());
						
						List<Map<String, String>> list = loginService.getFuncTree(loginBean.getLogin_id());
						JSONArray jsonArray = new JSONArray();
						String json = jsonArray.fromObject(list).toString();
						
						loginBean.setJsonFunc(json);
						request.setAttribute("loginBean", loginBean);
						url = "/jsp/index.jsp";
					}
				} else {
					url = "/jsp/login.jsp";
				}
				request.setAttribute("returnBean", returnBean);
			} catch (Exception e) {
				e.printStackTrace();
				url = "/jsp/login.jsp";
			}
			
			request.getRequestDispatcher(url).forward(request, response);
		} else if ("changePasswd".equals(method)) {
			request.getRequestDispatcher("/jsp/login/changePd_main.jsp").forward(request, response);
		} else if ("changePasswdSave".equals(method)) {
			ReturnBean returnBean = new ReturnBean();
			
			loginBean.setNew_passwd(MD5Utils.encode(loginBean.getNew_passwd()));
			loginBean.setOld_passwd(MD5Utils.encode(loginBean.getOld_passwd()));
			loginBean.setLogin_id(this.getSessionBean(request).getLogin_id());
			if (!loginService.updateLoginPwd(loginBean)) {
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("操作失败");
			}
			
			JSONObject jsonObject = new JSONObject();
			String json = jsonObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("changePasswdCheck".equals(method)) {
			boolean sign = false;
			String login_id = this.getSessionBean(request).getLogin_id();
			if(login_id != null && !"".equals(login_id)){
				loginBean.setLogin_id(login_id);
				loginBean.setLogin_pd(loginBean.getOld_passwd());
			}
			
			ReturnBean returnBean = loginService.loginCheck(loginBean);
			
			if(CommonUtils.SERVICE_RETURN_SUCCESS.equals(returnBean.getReturnValue())){
				sign = true;
			}
			this.sendJson(response, String.valueOf(sign));
		} else if ("upSelfMain".equals(method)) {
			
			Map<String, String> loginMap = loginService.getLoginInfo(loginBean);
			request.setAttribute("loginMap", loginMap);
			request.setAttribute("roleDictList", loginService.getRoleDict());
			request.setAttribute("statusList", loginService.getBaseDict("02"));
			
			request.getRequestDispatcher("/jsp/login/login_update_self.jsp").forward(request, response);
		} else if ("upSelfSave".equals(method)) {
			
			
			String basePath = request.getSession().getServletContext().getRealPath("/");
			String filePath = basePath + "file" + File.separator + "system" + File.separator;
			
			File srcFile = new File(loginBean.getPortrait_url());
			System.out.println(srcFile.getPath());
			String fileName = this.getFileName(srcFile.getName());
			
			FileUtils.copyInputStreamToFile(loginBean.getPortrait_url(), filePath + fileName);
			
			String networkPath  = request.getSession().getServletContext().getContextPath() + "/file/system/" + fileName;
			System.out.println(networkPath);
			loginBean.setPortrait_url(networkPath);
			ReturnBean returnBean = new ReturnBean();
			
			if (!loginService.updateLogin(loginBean)) {
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("操作失败");
			}
			
			JSONObject jsonObject = new JSONObject();
			String json = jsonObject.fromObject(returnBean).toString();
			this.sendJson(response, json);
		} else if ("logout".equals(method)) {
			request.getSession().invalidate();
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		} else if ("checkLoginId".equals(method)) {
			Map<String, String> map = loginService.getLoginInfo(loginBean);
			if(map == null){
				this.sendJson(response, "true");
				return;
			}
			this.sendJson(response, "false");
		}
	}
}
