package com.erp.sale.dao;

import com.erp.sale.bean.SaleBean;
import com.formwork.base.dao.BaseDao;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.daoimp.SqlImpDao;
import com.zrzy.people.bean.PeopleBean;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class SaleDao extends BaseDao {

    public PageBean queryList(SaleBean saleBean, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select * from saleorderdetail_info";

        PageBean pageUtil = this.pageUtil(sql, inParams, saleBean, sqlImpDao);

        return pageUtil;
    }
}
