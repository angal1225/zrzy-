package com.formwork.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.formwork.base.bean.CommonUtils;
import com.formwork.base.bean.SessionBean;

//@WebFilter(
//		filterName = "sessionFilter",
//		urlPatterns = "/*",
//		initParams = {
//				@WebInitParam(name = "loginpath", value = "/login/login"),
//				@WebInitParam(name = "ignore", value = "login.jsp,login,loginCheck"),
//				@WebInitParam(name = "ignoreTypes", value = ".gif,.bmp,.png,.jpg,.js,.flash,.css,.htm,.html")
//		})
public class SessionFilter implements Filter {
	
	private String loginflag;
	private String loginpath;
	private String ignore;
	private String ignoreTypes;
	private ServletContext context;

	public void init(FilterConfig config) throws ServletException {
		this.loginflag = CommonUtils.SERVICE_SESSIONBEAN;
		this.loginpath = config.getInitParameter("loginpath");
		this.ignore = config.getInitParameter("ignore");
		this.ignoreTypes = config.getInitParameter("ignoreTypes");
		context = config.getServletContext();
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		String requestURI=request.getRequestURI();
		String serverName=request.getContextPath();
		
		if(isIgnore(requestURI) || isignoreTypes(requestURI)){
			filterChain.doFilter(request, response);
			return;
		}
		
		if(!isSession(request)){
			response.sendRedirect(serverName+loginpath);
			return;
		}
		if(!isLogin(request)){
			response.sendRedirect(serverName+loginpath);
			return;
		}
		
		filterChain.doFilter(request, response);
	}

	private boolean isIgnore(String requestURI){
		if(ignore==null){
			return false;
		}	
		
		if(requestURI.equals(loginpath)){
			return true;
		}
		
		String url[]=ignore.split(",");
		for(int i=0;i<url.length;i++){
			if(requestURI.endsWith(url[i])){
				return true;
			}
		}
		
		return false;	
	}

	private boolean isignoreTypes(String requestURI){
		if(ignoreTypes==null){
			return false;
		}	
		
		if(requestURI.equals(loginpath)){
			return true;
		}
		
		String url[]=ignoreTypes.split(",");
		for(int i=0;i<url.length;i++){
			if(requestURI.indexOf(url[i])!=-1){
				return true;
			}
		}
		
		return false;	
	}
	
	private boolean isSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute(loginflag);
		if(sessionBean==null){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean isLogin(HttpServletRequest request){
		String session_now = request.getSession().getId();
		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute(loginflag);
		String session_old = (String) context.getAttribute(sessionBean.getLogin_id());
		if(session_old!=null && session_now.equals(session_old)){
			return true;
		}
		return false;
	}
	
	public void destroy() {
		context = null;
	}
}
