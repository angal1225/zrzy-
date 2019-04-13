package com.erp.sale.service;

import com.erp.sale.bean.SaleBean;
import com.erp.sale.dao.SaleDao;
import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.daoimp.SqlImpDao;
import com.zrzy.people.bean.PeopleBean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/2 0002
 */
    public class SaleService extends BaseService {
        private SaleDao saleDao = new SaleDao();
        public PageBean queryList(SaleBean saleBean){
            SqlImpDao sqlImpDao = new SqlImpDao();
            PageBean queryList = saleDao.queryList(saleBean, sqlImpDao);
            sqlImpDao.close();
            return queryList;
        }
    }
