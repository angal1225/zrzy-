package com.zrzy.people.servlet;

import java.io.IOException;
import java.util.Enumeration;
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

@WebServlet("/servlet/PeopleServlet/*")
public class PeopleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private PeopleService peopleService = new PeopleService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PeopleBean peopleBean = this.getParameterAllMap(request, PeopleBean.class);
		String method = peopleBean.getMethod();
		
		if("main".equals(method)){
			request.getRequestDispatcher("/jsp/people/people_main.jsp").forward(request, response);
		}else if("queryList".equals(method)){
			PageBean pageBean = peopleService.queryList(peopleBean);
			String resultString = JSONObject.fromObject(pageBean).toString();
			System.out.println(resultString);
			this.sendJson(response, resultString);
		}else if("add".equals(method)){
			List<Map<String, String>> sexList = peopleService.sexList();
			request.setAttribute("sexList", sexList);
			request.getRequestDispatcher("/jsp/people/people_add.jsp").forward(request, response);
		}else if("checkPeopleId".equals(method)){
			boolean sign = peopleService.checkPeopleId(peopleBean.getPeople_id());
			this.sendJson(response, String.valueOf(sign));
		}else if("addSave".equals(method)){
			boolean sign = peopleService.addSave(peopleBean);
			ReturnBean returnBean = new ReturnBean();
			if(sign){
				returnBean.setReturnInfo("新增成功哈哈哈！");
				returnBean.setReturnValue("true");
			}else{
				returnBean.setReturnInfo("新增失败哈哈哈！");
				returnBean.setReturnValue("false");
			}

			String resultString = JSONObject.fromObject(returnBean).toString();
			this.sendJson(response, String.valueOf(resultString));
		}else if("upMain".equals(method)){
			Map<String, String> peopleMap = peopleService.queryInfo(peopleBean.getPeople_id());
			request.setAttribute("peopleMap", peopleMap);
			List<Map<String, String>> sexList = peopleService.sexList();
			request.setAttribute("sexList", sexList);
			request.getRequestDispatcher("/jsp/people/people_update.jsp").forward(request, response);
		}else if("upSave".equals(method)){
			boolean sign = peopleService.upSave(peopleBean);
			this.sendJson(response, String.valueOf(sign));
		}else if("deleteSave".equals(method)){
			boolean sign = peopleService.deleteSave(peopleBean);
			request.setAttribute("sign", sign);
			request.getRequestDispatcher("/jsp/people/people_main.jsp").forward(request, response);
		}else if("deleteSaveBat".equals(method)){
			boolean sign = peopleService.deleteSaveBat(peopleBean.getPeople_ids());
			request.setAttribute("sign", sign);
			request.getRequestDispatcher("/jsp/people/people_main.jsp").forward(request, response);
		}else if("uploadTest".equals(method)){
			request.getRequestDispatcher("/jsp/people/people_upload.jsp").forward(request, response);
		}else if("upload".equals(method)){
			System.out.println(peopleBean.getPeople_test_file());
			
			FileUtils.copyInputStreamToFile(peopleBean.getPeople_test_file(), "");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
