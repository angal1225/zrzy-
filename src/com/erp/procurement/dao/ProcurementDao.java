package com.erp.procurement.dao;

import com.erp.procurement.bean.ProcurementBean;
import com.formwork.base.dao.BaseDao;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.daoimp.SqlImpDao;
import com.zrzy.people.bean.PeopleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class ProcurementDao extends BaseDao {

    public PageBean queryList(ProcurementBean procurementBean, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select * from prequisition_info";

        PageBean pageUtil = this.pageUtil(sql, inParams, procurementBean, sqlImpDao);

        return pageUtil;
    }
}
