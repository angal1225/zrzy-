package com.erp.baisc.supplier.dao;

import com.erp.baisc.supplier.bean.SupplierBean;
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
 * Date: 2019/4/1 0001
 */
public class SupplierDao extends BaseDao {

    public PageBean queryList(SupplierBean supplierBean, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select a.supplierId, a.supplierName, a.address, "
                + "a.contacts, a.phone, a.mailBox "
                + "from supplier_info a "
                + "where 0=0";

        if(supplierBean.getSupplierName() != null && !"".equals(supplierBean.getSupplierName())){
            sql += " and a.people_name like ?";
            inParams.add(new JdbcParameter("people_name", Types.VARCHAR, "%" + supplierBean.getSupplierName() + "%"));
        }
        PageBean pageUtil = this.pageUtil(sql, inParams, supplierBean, sqlImpDao);

        return pageUtil;
    }
    public static Map<String, String> queryInfo(String supplierId, SqlImpDao sqlImpDao){
        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        String sql = "select a.supplierId, a.supplierName, a.address, "
                + "a.contacts, a.phone, a.mailBox"
                + "from supplier_info a "
                + "where a.supplierId = ?";
        inParams.add(new JdbcParameter("supplierId", Types.VARCHAR, supplierId));

        List<Map<String, String>> queryDataMap = sqlImpDao.queryDataMap(sql, inParams);
        if(queryDataMap != null && queryDataMap.size() > 0){
            return queryDataMap.get(0);
        }

        return null;
    }

    public boolean checkSupplierId(String supplierId, SqlImpDao sqlImpDao){
        String sql = "select count(0) from supplier_info a "
                + "where supplierId = ?";

        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        inParams.add(new JdbcParameter("supplierId", Types.VARCHAR, supplierId));
        long count = sqlImpDao.queryCount(sql, inParams);
        if(count > 0){
            return false;
        }
        return true;
    }

    public static boolean addSave(SupplierBean supplierBean, SqlImpDao sqlImpDao){
        String sql = "insert into "
                + "supplier_info(supplierId, supplierName, address, "
                + "contacts, phone, mailBox) "
                + "values(?, ?, ?, ?, ?, ?)";

        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        inParams.add(new JdbcParameter("supplierId", Types.VARCHAR, supplierBean.getSupplierId()));
        inParams.add(new JdbcParameter("supplierName", Types.VARCHAR, supplierBean.getSupplierName()));
        inParams.add(new JdbcParameter("address", Types.VARCHAR, supplierBean.getAddress()));
        inParams.add(new JdbcParameter("contacts", Types.VARCHAR, supplierBean.getContacts()));
        inParams.add(new JdbcParameter("phone", Types.VARCHAR, supplierBean.getPhone()));
        inParams.add(new JdbcParameter("mailBox", Types.VARCHAR, supplierBean.getMailBox()));
        int numb = sqlImpDao.updateData(sql, inParams);

        if(numb > 0){
            return true;
        }
        return false;
    }

    public boolean upSave(SupplierBean supplierBean, SqlImpDao sqlImpDao){
        String sql = "update supplier_info set "
                + "supplierName = ?, address = ?, contacts = ?, phone = ?, "
                + "mailBox = ? where supplierId = ?";

        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        inParams.add(new JdbcParameter("supplierName", Types.VARCHAR, supplierBean.getSupplierName()));
        inParams.add(new JdbcParameter("address", Types.VARCHAR, supplierBean.getAddress()));
        inParams.add(new JdbcParameter("contacts", Types.VARCHAR, supplierBean.getContacts()));
        inParams.add(new JdbcParameter("phone", Types.VARCHAR, supplierBean.getPhone()));
        inParams.add(new JdbcParameter("mailBox", Types.VARCHAR, supplierBean.getMailBox()));
        inParams.add(new JdbcParameter("supplierId", Types.VARCHAR, supplierBean.getSupplierId()));
        int numb = sqlImpDao.updateData(sql, inParams);

        if(numb > 0){
            return true;
        }
        return false;
    }

    public boolean deleteSave(String supplierId, SqlImpDao sqlImpDao){
        String sql = "delete from supplier_info where supplierId = ?";

        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
        inParams.add(new JdbcParameter("supplierId", Types.VARCHAR, supplierId));
        int numb = sqlImpDao.updateData(sql, inParams);

        if(numb > 0){
            return true;
        }
        return false;
    }

}
