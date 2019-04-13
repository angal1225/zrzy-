package com.erp.sale.servlet;

import com.erp.sale.bean.SaleBean;
import com.erp.sale.service.SaleService;
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
@WebServlet("/servlet/TrendServlet/*")

public class TrendServlet extends BaseServlet {

    SaleService saleService = new SaleService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SaleBean saleBean = this.getParameterAllMap(request, SaleBean.class);
        String method = saleBean.getMethod();

        if("main".equals(method)){
            request.getRequestDispatcher("/jsp/statistics/Trend_main.jsp").forward(request, response);
        }else if("queryList".equals(method)){
            PageBean pageBean = saleService.queryList(saleBean);
            String resultString = JSONObject.fromObject(pageBean).toString();
            System.out.println(resultString);
            this.sendJson(response, resultString);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
