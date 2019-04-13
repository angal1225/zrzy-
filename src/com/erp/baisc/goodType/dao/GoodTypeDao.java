package com.erp.baisc.goodType.dao;

import com.erp.baisc.goodType.bean.GoodTypeBean;
import com.formwork.base.dao.BaseDao;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.daoimp.SqlImpDao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class GoodTypeDao extends BaseDao {

    public PageBean queryList(GoodTypeBean goodTypeBean, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select * from goodtype_info";
        PageBean pageUtil = this.pageUtil(sql, inParams, goodTypeBean, sqlImpDao);
        return pageUtil;
    }

    public Map<String, String> queryInfo(int goodTypeId, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select goodTypeId, goodTypeName"
                + "from goodtype_info "
                + "where goodTypeId = ?";
        inParams.add(new JdbcParameter("goodTypeId", Types.VARCHAR, goodTypeId));

        List<Map<String, String>> queryDataMap = sqlImpDao.queryDataMap(sql, inParams);
        if(queryDataMap != null && queryDataMap.size() > 0){
            return queryDataMap.get(0);
        }

        return null;
    }

    public boolean deleteSave(String goodTypeId, SqlImpDao sqlImpDao){
        String sql = "delete from goodtype_info where goodTypeId = ?";

        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        inParams.add(new JdbcParameter("goodTypeId", Types.VARCHAR, goodTypeId));
        int numb = sqlImpDao.updateData(sql, inParams);

        if(numb > 0){
            return true;
        }
        return false;
    }

}
