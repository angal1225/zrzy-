package com.erp.baisc.supplier.service;

import com.erp.baisc.supplier.bean.SupplierBean;
import com.erp.baisc.supplier.dao.SupplierDao;
import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.daoimp.SqlImpDao;
import com.zrzy.people.bean.PeopleBean;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : 徐大伟
 * User: ${和敬清寂}
 * Date: 2019/4/1 0001
 */
public class SupplierService extends BaseService {

    private SupplierDao supplierDao = new SupplierDao();
    private SupplierBean supplierBean;

    public PageBean queryList(SupplierBean supplierBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        PageBean queryList = supplierDao.queryList(supplierBean, sqlImpDao);
        sqlImpDao.close();
        return queryList;
    }

    public boolean checkSupplierId(String supplierId){
        SqlImpDao sqlImpDao = new SqlImpDao();
        boolean checkSupplierId = supplierDao.checkSupplierId(supplierId, sqlImpDao);
        sqlImpDao.close();
        return checkSupplierId;
    }

    public boolean addSave(SupplierBean supplierBean){
        SqlImpDao sqlImpDao = new SqlImpDao();
        boolean sign = (boolean) SupplierDao.addSave(supplierBean, sqlImpDao);
        if(sign){
            sqlImpDao.commit();
        }else{
            sqlImpDao.rollback();
        }
        sqlImpDao.close();
        return sign;
    }

    public static Map<String,String> queryInfo(String supplierId){
        SqlImpDao sqlImpDao = new SqlImpDao();
        Map<String, String> queryInfo = SupplierDao.queryInfo(supplierId, sqlImpDao);
        sqlImpDao.close();
        return queryInfo;
    }

    public boolean upSave(SupplierDao supplierDao){
        SqlImpDao sqlImpDao = new SqlImpDao();
        boolean sign = supplierDao.upSave(supplierBean, sqlImpDao);
        if(sign){
            sqlImpDao.commit();
        }else{
            sqlImpDao.rollback();
        }
        sqlImpDao.close();
        return sign;
    }

    public boolean deleteSave(SupplierDao supplierDao){
        SqlImpDao sqlImpDao = new SqlImpDao();
        boolean sign = supplierDao.deleteSave(supplierBean.getSupplierId(), sqlImpDao);
        if(sign){
            sqlImpDao.commit();
        }else{
            sqlImpDao.rollback();
        }
        sqlImpDao.close();
        return sign;
    }

    public boolean deleteSaveBat(String supplierIds){
        SqlImpDao sqlImpDao = new SqlImpDao();
        String[] supplierId_array = supplierIds.split(",");
        for(String supplierId : supplierId_array){
            supplierDao.deleteSave(supplierId, sqlImpDao);
        }
        sqlImpDao.commit();
        sqlImpDao.close();

        return true;
    }
}
