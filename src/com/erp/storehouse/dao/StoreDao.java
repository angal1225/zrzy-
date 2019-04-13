package com.erp.storehouse.dao;

import com.erp.storehouse.bean.StoreBean;
import com.formwork.base.dao.BaseDao;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.daoimp.SqlImpDao;

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
public class StoreDao extends BaseDao {

    public PageBean queryList(StoreBean storeBean, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select * from warehouse_info";

//        if(storeBean.getWarehouseName() != null && !"".equals(storeBean.getWarehouseName())){
//            sql += " and a.people_name like ?";
//            inParams.add(new JdbcParameter("people_name", Types.VARCHAR, "%" + storeBean.getWarehouseName() + "%"));
//        }

        PageBean pageUtil = this.pageUtil(sql, inParams, storeBean, sqlImpDao);

        return pageUtil;
    }
}
