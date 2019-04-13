package com.erp.procurement.service;

import com.erp.procurement.bean.ProcurementBean;
import com.erp.procurement.dao.ProcurementDao;
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
public class ProcurementService extends BaseService {

    private ProcurementDao procurementDao = new ProcurementDao();
    public PageBean queryList(ProcurementBean procurementBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        PageBean queryList = procurementDao.queryList(procurementBean, sqlImpDao);
        sqlImpDao.close();
        return queryList;
    }
}
