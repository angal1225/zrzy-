package com.erp.baisc.good.servlet;

import com.erp.baisc.good.bean.GoodBean;
import com.erp.baisc.good.service.GoodService;
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
@WebServlet("/servlet/GoodServlet/*")
public class GoodServlet extends BaseServlet {
    private GoodService goodService = new GoodService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GoodBean goodBean = this.getParameterAllMap(request, GoodBean.class);
        String method = goodBean.getMethod();

        if ("main".equals(method)) {
            request.getRequestDispatcher("/jsp/good/good_main.jsp").forward(request, response);
        } else if ("queryList".equals(method)) {
            PageBean pageBean = goodService.queryList(goodBean);
            String resultString = JSONObject.fromObject(pageBean).toString();
            System.out.println(resultString);
            this.sendJson(response, resultString);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
}
