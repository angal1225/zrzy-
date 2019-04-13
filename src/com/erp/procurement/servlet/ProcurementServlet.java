package com.erp.procurement.servlet;

import com.erp.procurement.bean.ProcurementBean;
import com.erp.procurement.service.ProcurementService;
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
@WebServlet("/servlet/ProcurementServlet/*")
public class ProcurementServlet extends BaseServlet {
    private ProcurementService procurementService = new ProcurementService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProcurementBean procurementBean = this.getParameterAllMap(request, ProcurementBean.class);
        String method = procurementBean.getMethod();

        if("main".equals(method)){
            request.getRequestDispatcher("/jsp/procurement/procurement_mian.jsp").forward(request, response);
        }else if("queryList".equals(method)){
            PageBean pageBean = procurementService.queryList(procurementBean);
            String resultString = JSONObject.fromObject(pageBean).toString();
            System.out.println(resultString);
            this.sendJson(response, resultString);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
