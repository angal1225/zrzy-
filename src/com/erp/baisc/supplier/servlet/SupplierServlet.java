package com.erp.baisc.supplier.servlet;

import com.erp.baisc.supplier.bean.SupplierBean;
import com.erp.baisc.supplier.service.SupplierService;
import com.formwork.base.bean.ReturnBean;
import com.formwork.base.servlet.BaseServlet;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/1 0001
 */
@WebServlet(name = "SupplierServlet")
public class SupplierServlet extends BaseServlet {
    private SupplierService supplierService = new SupplierService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
