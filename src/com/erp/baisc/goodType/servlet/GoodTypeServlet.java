package com.erp.baisc.goodType.servlet;

import com.erp.baisc.goodType.bean.GoodTypeBean;
import com.erp.baisc.goodType.service.GoodTypeService;
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
@WebServlet("/servlet/GoodTypeServlet/*")
public class GoodTypeServlet extends BaseServlet {
    private GoodTypeService goodTypeService = new GoodTypeService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GoodTypeBean goodTypeBean = this.getParameterAllMap(request, GoodTypeBean.class);
        String method = goodTypeBean.getMethod();

        if ("main".equals(method)) {
            request.getRequestDispatcher("/jsp/good/goodType_main.jsp").forward(request, response);
        } else if ("queryList".equals(method)) {
            System.out.println(goodTypeBean);
            PageBean pageBean = goodTypeService.queryList(goodTypeBean);
            String resultString = JSONObject.fromObject(pageBean).toString();
            System.out.println(resultString);
            this.sendJson(response, resultString);
        } else if ("deleteSave".equals(method)) {
            boolean sign = goodTypeService.deleteSave(goodTypeBean);
            request.setAttribute("sign", sign);
            request.getRequestDispatcher("/jsp/good/goodType_main.jsp").forward(request, response);
        } else if ("deleteSaveBat".equals(method)) {
            boolean sign = goodTypeService.deleteSaveBat(goodTypeBean.getGoodTypeIds());
            request.setAttribute("sign", sign);
            request.getRequestDispatcher("/jsp/good/goodType_main.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
