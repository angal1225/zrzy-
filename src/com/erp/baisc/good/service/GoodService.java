package com.erp.baisc.good.service;

import com.erp.baisc.good.bean.GoodBean;
import com.erp.baisc.good.dao.GoodDao;
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
public class GoodService extends BaseService {

    private GoodDao goodDao = new GoodDao();
    public PageBean queryList(GoodBean goodBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        PageBean queryList = goodDao.queryList(goodBean, sqlImpDao);
        sqlImpDao.close();
        return queryList;
    }
}
