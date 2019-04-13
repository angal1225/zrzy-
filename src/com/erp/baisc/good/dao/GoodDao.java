package com.erp.baisc.good.dao;

import com.erp.baisc.good.bean.GoodBean;
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
public class GoodDao extends BaseDao {

    public PageBean queryList(GoodBean goodBean, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select * from good_info";

//        if(goodBean.getGoodName() != null && !"".equals(goodBean.getGoodName())){
//            sql += " and a.people_name like ?";
//            inParams.add(new JdbcParameter("people_name", Types.VARCHAR, "%" + goodBean.getGoodName() + "%"));
//        }
        PageBean pageUtil = this.pageUtil(sql, inParams, goodBean, sqlImpDao);

        return pageUtil;
    }
}
