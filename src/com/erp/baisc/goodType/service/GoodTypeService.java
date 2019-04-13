package com.erp.baisc.goodType.service;

import com.erp.baisc.goodType.bean.GoodTypeBean;
import com.erp.baisc.goodType.dao.GoodTypeDao;
import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.daoimp.SqlImpDao;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class GoodTypeService extends BaseService {

    private GoodTypeDao goodTypeDao = new GoodTypeDao();

    public PageBean queryList(GoodTypeBean goodTypeBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        PageBean queryList = goodTypeDao.queryList(goodTypeBean, sqlImpDao);
        sqlImpDao.close();
        return queryList;
    }

    public Map<String,String> queryInfo(int goodTypeId){
        SqlImpDao sqlImpDao = new SqlImpDao();
        Map<String, String> queryInfo = goodTypeDao.queryInfo(goodTypeId, sqlImpDao);
        sqlImpDao.close();
        return queryInfo;
    }

    public boolean deleteSave(GoodTypeBean goodTypeBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        boolean sign = goodTypeDao.deleteSave(goodTypeBean.getGoodTypeId(),sqlImpDao);
        if(sign){
            sqlImpDao.commit();
        }else{
            sqlImpDao.rollback();
        }
        sqlImpDao.close();
        return sign;
    }

    public boolean deleteSaveBat(String goodTypeIds){
        SqlImpDao sqlImpDao = new SqlImpDao();
        String[] goodTypeId_array = goodTypeIds.split(",");
        for(String goodTypeId : goodTypeId_array){
            goodTypeDao.deleteSave(goodTypeId, sqlImpDao);
        }
        sqlImpDao.commit();
        sqlImpDao.close();

        return true;
    }
}
