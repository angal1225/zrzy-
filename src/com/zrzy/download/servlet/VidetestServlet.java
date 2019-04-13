package com.zrzy.download.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

@WebServlet("/servlet/download/")
public class VidetestServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = new String("太原中软《Java互联网应用开发工程师教学大纲》整理.xlsx".getBytes(), "iso-8859-1");
		response.setHeader("Content-Disposition", "attachment;fileName="+name);
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		FileInputStream fileInputStream = new FileInputStream("D:\\jxdg\\太原中软《Java互联网应用开发工程师教学大纲》整理.xlsx");
		byte[] bs = new byte[1024];
		
		while(fileInputStream.read(bs) != -1){
			outputStream.write(bs);
			bs = new byte[1024];
		}
		outputStream.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
