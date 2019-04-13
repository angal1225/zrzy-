package com.formwork.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName="methodFilter",urlPatterns="/*")
public class MethodFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        
        String servletPath = httpServletRequest.getRequestURI();
        String method = servletPath.substring(servletPath.lastIndexOf("/")+1);
        httpServletRequest.setAttribute("method", method);
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
 
    @Override
    public void destroy() {
    }
}