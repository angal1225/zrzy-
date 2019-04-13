package com.erp.storehouse.service;

import com.erp.storehouse.bean.StoreBean;
import com.erp.storehouse.dao.StoreDao;
import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.daoimp.SqlImpDao;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
public class StoreService extends BaseService {

    private StoreDao storeDao = new StoreDao();
    public PageBean queryList(StoreBean storeBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        PageBean queryList = storeDao.queryList(storeBean, sqlImpDao);
        sqlImpDao.close();
        return queryList;
    }
}
