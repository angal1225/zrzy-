package com.erp.storehouse.servlet;

import com.erp.storehouse.bean.StoreBean;
import com.erp.storehouse.service.StoreService;
import com.formwork.base.page.PageBean;
import com.formwork.base.servlet.BaseServlet;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
@WebServlet("/servlet/StoreServlet/*")
public class StoreServlet extends BaseServlet {
    private StoreService storeService = new StoreService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StoreBean storeBean = this.getParameterAllMap(request, StoreBean.class);
        String method = storeBean.getMethod();

        if("main".equals(method)){
            request.getRequestDispatcher("/jsp/store/store_main.jsp").forward(request, response);
        }else if("queryList".equals(method)){
            PageBean pageBean = storeService.queryList(storeBean);
            String resultString = JSONObject.fromObject(pageBean).toString();
            System.out.println(resultString);
            this.sendJson(response, resultString);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
}
